package com.itkevin.nettyplus.nettycommunication.core.tcp;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.ProtocolHelper;
import com.itkevin.nettyplus.nettycommunication.core.config.ServerConstant;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.core.context.Global;
import com.itkevin.nettyplus.nettycommunication.core.filter.IFilter;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.CommandHolder;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.IInvokerHandler;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.IProxyStub;
import com.itkevin.nettyplus.nettycommunication.utils.ExceptionFactory;
import com.itkevin.nettyplus.nettycommunication.utils.NameableThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 异步执行业务代码并且返回数据
 * @author chengang
 *
 */
public class AsyncMessageInvoker implements IInvokerHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncMessageInvoker.class);

	private ExecutorService executor;
	
	public static final AsyncMessageInvoker INSTANCE = new AsyncMessageInvoker();
	
	public static AsyncMessageInvoker getInstance() {
		return INSTANCE;
	}
	
	private AsyncMessageInvoker() {
		this.executor = new ThreadPoolExecutor(
				ServerConstant.DEFAULT_THREAD_POOL_MIN_SIZE ,
				ServerConstant.DEFAULT_THREAD_POOL_MAX_SIZE , 
				60 , 
				TimeUnit.SECONDS ,
				new ArrayBlockingQueue<Runnable>(ServerConstant.DEFAULT_ACCEPT_COUNT) ,
				new NameableThreadFactory("server-pool" , false) ,
				new RejectedExecutionHandler() {

					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						LOGGER.warn("rejectedExecution --- ");
					}
					
				});
	}

	@Override
	public void invoke(final BeatContext context) {
		executor.execute(new Runnable() {
			
			@Override
			public void run() {
				//保持请求上下文
				BeatContext.setContext(context);
				try {
					IProxyStub proxy = CommandHolder.getProxy(context.getRequest().getMapping());
					if(proxy != null) {
						//请求过滤器
						LOGGER.debug("begin request filter");
						for(IFilter filter : Global.getInstance().getRequestFilterList()) {
							filter.filter(context);
						}
						//执行方法
						doInvoker(context , proxy);
						//应答过滤器
						LOGGER.debug("begin response filter");
						for(IFilter filter : Global.getInstance().getResponseFilterList()) {
							filter.filter(context);
						}
						//获取数据后返回消息
						context.writeResponse();
					} else {
						throw ExceptionFactory.createServiceFrameException("can not find a method to resolve this message", context, new NullPointerException());
					}
				} catch (ServiceFrameException e) {
					LOGGER.error(e.getMessage() , e);
					
					context.writeMessage(ProtocolHelper.createExceptionMessage(MessageFromType.SERVER, e));
				} catch (Exception e) {
					LOGGER.error(e.getMessage() , e);
					
					context.writeMessage(ProtocolHelper.createExceptionMessage(context.getMessageId() , MessageFromType.SERVER, context.getDeviceId() , e));
				} finally {
					BeatContext.clearContext();//清除上下文
				}
			}
		});
	}
	
	/**
	 * 处理请求
	 * @param context - BeatContext
	 * @param proxy - 代理方法实例
	 * @throws ServiceFrameException 
	 */
	private void doInvoker(BeatContext context , IProxyStub proxy) throws ServiceFrameException {
		LOGGER.debug("deviceId : " + context.getDeviceId()+",mapping : " + context.getMapping());
		//记录消息处理时间
		long beginTime = System.currentTimeMillis();
		context.setInvokeBeginTime(beginTime);
		try {
			//调用
			proxy.invoke(context);
		} finally {
			//消息完成处理时间
			long endTime = System.currentTimeMillis();
			//记录消息服务器响应时间
			context.setInvokeEndTime(endTime);
			context.getResponse().setTerminalTime(endTime);
		}
	}
	
	/**
	 * 停止线程池
	 */
	public void stop() {
		LOGGER.info("attempt to shutdown AsyncMessageInvoker.");
		try {
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			LOGGER.warn("shutdown interrupted");
		} finally {
			if (!executor.isTerminated()) {
				LOGGER.error("cancel non-finished tasks");
			}
			executor.shutdownNow();
		}
		LOGGER.info("AsyncMessageInvoker is closed.");
	}

}
