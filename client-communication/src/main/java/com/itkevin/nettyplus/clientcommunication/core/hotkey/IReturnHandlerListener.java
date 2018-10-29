package com.itkevin.nettyplus.clientcommunication.core.hotkey;


import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;

/**
 * 返回消息处理
 * @author chengang
 *
 */
public interface IReturnHandlerListener {
	
	public Object handler(IMessage message) throws Exception;

}
