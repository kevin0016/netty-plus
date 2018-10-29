package com.itkevin.nettyplus.communicationmessage.protocol.exception;

/**
 * 错误辅助类
 * @author chengang
 *
 */
public final class ThrowErrorHelper {
	
	/**
	 * 创建服务调用异常类对象
	 * @param errCode - 异常错误编号
	 * @param exception - 异常信息
	 * @return RemoteException
	 */
	public static RemoteException throwServiceError(int errCode, String exception) {
		ExceptionType exType = ExceptionType.getByCode(errCode);
		if(exType == null) {
			return new RemoteException(exception);
		}
		
		try {
			//通过反射创建异常类实例
			return (RemoteException)exType.getExceptionClass().getConstructor(String.class).newInstance(exception);
		} catch (Exception e) {
			return new RemoteException(exception);
		}
	}

}
