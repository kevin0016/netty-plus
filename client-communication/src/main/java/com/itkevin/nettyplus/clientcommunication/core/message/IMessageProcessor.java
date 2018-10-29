package com.itkevin.nettyplus.clientcommunication.core.message;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * 消息处理接口
 * @author chengang
 * 
 */
public interface IMessageProcessor {
	
	/**
	 * 接收到请求消息
	 * @param ctx - ChannelHandlerContext
	 * @param p - 消息体
	 */
	void processReceiveRequestMessage(ChannelHandlerContext ctx, Protocol p);
	
	/**
	 * 接收到响应消息
	 * @param ctx - ChannelHandlerContext
	 * @param p - 消息体
	 */
	void processReceiveResponseMessage(ChannelHandlerContext ctx, Protocol p);
	
	/**
	 * 接收到异常消息
	 * @param ctx - ChannelHandlerContext
	 * @param p - 消息体
	 */
	void processReceiveExceptionMessage(ChannelHandlerContext ctx, Protocol p);
	
	/**
	 * 接受到状态变更消息
	 * @param ctx - ChannelHandlerContext
	 * @param message - 消息体
	 */
	void processReceiveStatusMessage(ChannelHandlerContext ctx, StatusMessage message);

}
