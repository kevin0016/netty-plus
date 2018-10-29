package com.itkevin.nettyplus.clientcommunication.core.message;

import com.itkevin.nettyplus.clientcommunication.core.hotkey.CallBackEvent;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.IAsyncCallBackListener;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.IReturnHandlerListener;
import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;

public class WindowData {
	
	/**
	 * 是否是异步调用
	 */
	private final boolean async;
	
	/**
	 * 如果是同步的请求，将有此字段，如果是异步的event则为空
	 */
	private final AutoReceiveEvent event;
	
	/**
	 * 存储同步请求的返回协议对象，如果是异步调用，也会存储发送的协议对象
	 */
	private Protocol protocol;
	
	/**
	 * 消息ID
	 */
	private final int messageId;
	
	/**
	 * 异步回调对象数组
	 */
	private final IAsyncCallBackListener[] callBacks;
	
	/**
	 * 异步回调的方法详情
	 */
	private final IReturnHandlerListener returnHandler;
	
	/**
	 * 对象生成的时间戳
	 */
	private final long timestamp;
	
	/**
	 * 异步回调是否是Future模式
	 */
	private final boolean future;
	
	public WindowData(int messageId , AutoReceiveEvent event) {
		this.async = false;
		this.messageId = messageId;
		this.event = event;
		this.callBacks = null;
		this.returnHandler = null;
		this.timestamp = System.currentTimeMillis();
		this.future = false;
	}
	
	public WindowData(int messageId , Protocol protocol , IReturnHandlerListener returnHandler , IAsyncCallBackListener[] callBacks , boolean future) {
		this.async = true;
		this.protocol = protocol;
		this.messageId = messageId;
		this.event = null;
		this.callBacks = callBacks;
		this.returnHandler = returnHandler;
		this.timestamp = System.currentTimeMillis();
		this.future = future;
	}

	public AutoReceiveEvent getEvent() {
		return event;
	}

	public Protocol getProtocol() {
		return protocol;
	}

	public void setProtocol(Protocol protocol) {
		this.protocol = protocol;
	}

	public boolean isAsync() {
		return async;
	}

	public IAsyncCallBackListener[] getCallBacks() {
		return callBacks;
	}

	public int getMessageId() {
		return messageId;
	}

	public IReturnHandlerListener getReturnHandler() {
		return returnHandler;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public boolean isFuture() {
		return future;
	}

	/**
	 * 执行异步Callback回调
	 * @param event - CallBackEvent
	 */
	public void handleCallback(CallBackEvent event) {
		if(event == null) {
			throw new NullPointerException("call back event has not null!");
		}
		
		if(this.callBacks != null && this.callBacks.length > 0) {
			for(IAsyncCallBackListener listener : this.callBacks) {
				if(!listener.callback(event)) {
					break;
				}
			}
		}
	}

}
