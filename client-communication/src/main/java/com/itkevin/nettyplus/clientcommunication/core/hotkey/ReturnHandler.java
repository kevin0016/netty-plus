package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ProtocolException;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.RemoteException;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ThrowErrorHelper;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ExceptionMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
/**
 * 处理返回值
 * @author chengang
 *
 */
public final class ReturnHandler {
	
	/**
	 * 处理返回消息，创建返回值对象
	 * @param methodInfo - MethodInfo
	 * @param message - IMessage
	 * @return Object
	 * @throws RemoteException
	 */
	public static Object handle(MethodInfo methodInfo , IMessage message) throws RemoteException {
		MessageType type = message.messageType();
		final Class<?> returnType = methodInfo.getReturnType();
		switch(type) {
			case Response:
				if(returnType == void.class) {//不需要返回值的方法。则直接return null;
					return null;
				} else if(returnType == ResponseMessage.class) {//此处判断如果返回值是ResponseMessage类型，则直接return message
					return message;
				} else {//需要解析body字段，用JSON转换
					return FastJsonHelper.toObject(((ResponseMessage)message).getBody(), returnType);
				}
			case Exception:
				ExceptionMessage ex = (ExceptionMessage)message;
				throw ThrowErrorHelper.throwServiceError(ex.getErrCode(), ex.getErrMsg());
			default :
				throw new ProtocolException("Unable to process data information!");
		}
	}
	
	private ReturnHandler() {
		
	}

}
