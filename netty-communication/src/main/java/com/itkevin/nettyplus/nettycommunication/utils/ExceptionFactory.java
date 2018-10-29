package com.itkevin.nettyplus.nettycommunication.utils;

import com.itkevin.nettyplus.communicationmessage.protocol.exception.ExceptionType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**
 * 异常创建工具类
 * @author chengang
 *
 */
public final class ExceptionFactory {
	
	/**
	 * 创建服务调用异常类
	 * @param errMsg - 错误信息
	 * @param messageId - 消息ID
	 * @param deviceId - 设备ID
	 * @param ex Throwable
	 * @return ServiceFrameException
	 */
	public static ServiceFrameException createServiceFrameException(String errMsg , int messageId , String deviceId , Throwable ex) {
		return createServiceFrameException(errMsg, messageId , deviceId , null , null , null , ExceptionType.OTHER_EXCEPTION, ex);
	}
	
	/**
	 * 创建服务调用异常类
	 * @param errMsg - 错误信息
	 * @param context - 消息上下文
	 * @param ex - Throwable
	 * @return ServiceFrameException
	 */
	public static ServiceFrameException createServiceFrameException(String errMsg , BeatContext context , Throwable ex) {
		return createServiceFrameException(errMsg, context, ExceptionType.OTHER_EXCEPTION, ex);
	}
	
	/**
	 * 创建服务调用异常类
	 * @param errMsg - 错误信息
	 * @param context - 消息上下文
	 * @param exceptionType - 错误类型
	 * @param ex - Throwable
	 * @return ServiceFrameException
	 */
	public static ServiceFrameException createServiceFrameException(String errMsg , BeatContext context , ExceptionType exceptionType , Throwable ex) {
		return new ServiceFrameException(errMsg ,
				context.getMessageId() ,
				context.getDeviceId() ,
				context.getRemoteIP() ,
				context.getLocalIP() ,
				context.getRequest() ,
				exceptionType ,
				ex);
	}
	
	/**
	 * 创建服务调用异常类
	 * @param errMsg - 错误信息
	 * @param messageId - 消息ID
	 * @param deviceId - 设备ID
	 * @param remoteIP - 远程调用IP
	 * @param localIP - 本机IP
	 * @param request - RequestMessage
	 * @param exceptionType - 错误类型
	 * @param ex - Throwable
	 * @return ServiceFrameException
	 */
	public static ServiceFrameException createServiceFrameException(String errMsg , int messageId , String deviceId , String remoteIP , String localIP , RequestMessage request , ExceptionType exceptionType , Throwable ex) {
		return new ServiceFrameException(errMsg ,
				messageId ,
				deviceId ,
				remoteIP ,
				localIP ,
				request ,
				exceptionType ,
				ex);
	}
	
	private ExceptionFactory() {
		
	}

}
