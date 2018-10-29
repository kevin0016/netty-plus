package com.itkevin.nettyplus.clientcommunication.core.message;

import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.*;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionFactory;
import com.itkevin.nettyplus.clientcommunication.core.utils.SystemUtils;
import com.itkevin.nettyplus.clientcommunication.core.utils.ThreadRenameFactory;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步消息处理类
 * @author chengang
 *
 */
public final class AsyncMessageProcessor {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMessageProcessor.class);
	
	/**
	 * 异步发送线程池
	 */
	private static final ThreadPoolExecutor SEND_EXECUTOR = new ThreadPoolExecutor(1, 1, 1500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadRenameFactory("AsyncHandler-Send-Thread"));
	
	/**
	 * 超时队列
	 */
	private static final LinkedBlockingQueue<WindowData> TIMEOUT_QUEUE = new LinkedBlockingQueue<>();
	
	/**
	 * 异步回调线程池
	 */
	private static final ThreadPoolExecutor CALLBACK_EXECUTOR = new ThreadPoolExecutor(SystemUtils.getSystemThreadCount(),SystemUtils.getSystemThreadCount(),1500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),new ThreadRenameFactory("CallBackExecutor-Thread"));
	
	/**
	 * 空超时队列，线程等待的毫秒数
	 */
	private static final long EMPTY_TIMEOUT_QUEUE_SLEEP_MILLIS = 5000L;
	
	/**
	 * 未超时的队列，线程等待的毫秒数
	 */
	private static final long NOT_TIMEOUT_QUEUE_SLEEP_MILLIS = 1000L;
	
	/**
	 * 最大等待队列大小
	 */
	private static final int LINKED_BLOCK_SIZE = 2000;
	
	static {
		LOGGER.info("Init AsyncMessage Process START!");
		Thread th = new Thread(new RemotingInvocationTimeoutScan(), "AsyncHandler-TimeOut-Thread");
        th.setDaemon(true);
        th.start();
		LOGGER.info("Init AsyncMessage Process FINISH!");
	}
	
	/**
	 * 执行异步调用
	 * @param wd - WindowData
	 */
	public static void offerSendQueue(WindowData wd) {
		if (getSendQueueSize() > LINKED_BLOCK_SIZE || getTimeoutQueueSize() > LINKED_BLOCK_SIZE) {
			LOGGER.warn("async sendQueue size > " + LINKED_BLOCK_SIZE + " or timeout queue size > " + LINKED_BLOCK_SIZE);
			CallBackEvent event = new CallBackEvent(new ExceedCapacityException("sendQueue size > " + LINKED_BLOCK_SIZE));
			wd.handleCallback(event);
			return;
		}
		//发送
		sendMessage(wd);
	}
	
	/**
	 * 发送消息
	 * @param wd - WindowData
	 */
	private static void sendMessage(final WindowData wd) {
		SEND_EXECUTOR.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					SessionFactory.getSession().getCtx().writeAndFlush(wd.getProtocol());
					//添加到超时队列中
					TIMEOUT_QUEUE.offer(wd);
				} catch (Exception e) {
					LOGGER.error("async client write message Exception", e);
					//抛出异常了则移除定时器
					MessageWaitProcessor.unregisterEvent(wd.getMessageId());
				}
			}
		});
	}
	
	/**
	 * 异步回调
	 * @param wd
	 */
	public static void callback(final WindowData wd) {
		CALLBACK_EXECUTOR.execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					//处理返回消息，创建返回值对象
					Object returnObject;
					IReturnHandlerListener returnHandler = wd.getReturnHandler();
					if(returnHandler == null) {
						returnObject = wd.getProtocol().getEntity();
					} else {
						returnObject = returnHandler.handler((IMessage)wd.getProtocol().getEntity());
					}					
					
					IAsyncCallBackListener[] callbackListener = wd.getCallBacks();
					if(callbackListener != null && callbackListener.length > 0) {
						//执行回调
						CallBackEvent event = new CallBackEvent(returnObject);
						wd.handleCallback(event);
					} else {
						LOGGER.warn("async callback messageId : " + wd.getMessageId() + " , result : " + FastJsonHelper.toJson(returnObject) + " has not CallbackListener");
					}
				} catch (Exception e) {
					LOGGER.error("async client write message Exception", e);
				} finally {
					//则移除定时器
					MessageWaitProcessor.unregisterEvent(wd.getMessageId());
				}
			}
		});
	}
	
	/**
	 * 暂停线程池
	 * @throws Exception
	 */
	public static void stop() throws Exception {
		LOGGER.info("attempt to shutdown AsyncMessageProcessor.");
		
		//停止发送异步消息
		stopExecutor(SEND_EXECUTOR);
		//停止回调线程池
		stopExecutor(CALLBACK_EXECUTOR);
		
		LOGGER.info("AsyncMessageProcessor is stop.");
	}
	
	/**
	 * 停止线程池
	 * @param executor - ThreadPoolExecutor
	 */
	private static void stopExecutor(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.warn("shutdown interrupted");
		} finally {
			if (!SEND_EXECUTOR.isTerminated()) {
				LOGGER.error("cancel non-finished tasks");
			}
			executor.shutdownNow();
		}
	}
	
	/**
	 * 获取当前待发送队列的数量
	 * @return int
	 */
	public static int getSendQueueSize() {
		return SEND_EXECUTOR.getQueue().size();
	}
	
	/**
	 * 获得当前超时队列的数量
	 * @return int
	 */
	public static int getTimeoutQueueSize() {
		return TIMEOUT_QUEUE.size();
	}
	
	private static class RemotingInvocationTimeoutScan implements Runnable {

		@Override
		public void run() {
			final int asyncRequestTimeout = Global.getInstance().getClientConfig().getAsyncRequestTimeout();
			while(true) {
				try {
					WindowData wd = TIMEOUT_QUEUE.peek();
					if(wd != null) {
						//首先判断是否还在消息管理器中
						if(MessageWaitProcessor.hasMessageId(wd.getMessageId())) {
							//判断是否超时
							if(System.currentTimeMillis() - wd.getTimestamp() >= asyncRequestTimeout * 1000) {
								//移除消息，抛出异常
								TIMEOUT_QUEUE.poll();
								//从管理器中移除
								MessageWaitProcessor.unregisterEvent(wd.getMessageId());
								//异步执行Callback回调，反馈异步超时异常
								CALLBACK_EXECUTOR.execute(new Runnable() {
									
									@Override
									public void run() {
										//通知回调函数
										String timeoutMsg = "async timeout , messageId : " +wd.getMessageId() + ",sendData : " + FastJsonHelper.toJson(wd.getProtocol().getEntity());
										LOGGER.warn(timeoutMsg);
										CallBackEvent event = new CallBackEvent(new AsyncTimeoutException(timeoutMsg));
										wd.handleCallback(event);
									}
								});
							} else {
								//如果队列第一个消息还没有超时，则等待1秒
								Thread.sleep(NOT_TIMEOUT_QUEUE_SLEEP_MILLIS);
							}
						} else {
							LOGGER.info("current message not in message queue  , remove queue，message ID : " + wd.getMessageId());
							//移除
							TIMEOUT_QUEUE.poll();
						}
					} else {
						//如果当前队列为空，则多休息一会
						LOGGER.info("current message manager queue is empty , sleep three sec!");
						Thread.sleep(EMPTY_TIMEOUT_QUEUE_SLEEP_MILLIS);
					}
				} catch (Throwable e) {
                    LOGGER.error("Exception when scan the timeout invocation of remoting.", e);
                    try {
						Thread.sleep(EMPTY_TIMEOUT_QUEUE_SLEEP_MILLIS);
					} catch (InterruptedException e1) {}
                }
			}
		}
		
	}
	
	private AsyncMessageProcessor() {
		
	}

}
