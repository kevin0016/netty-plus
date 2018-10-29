package com.itkevin.nettyplus.communicationmessage.protocol.exception;

/**
 * 客户端调用超时异常
 * @author chengang
 *
 */
public class TimeoutException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4345935963121140739L;

	public TimeoutException() {
		super(ExceptionType.TIME_OUT);
	}

	public TimeoutException(String message) {
		super(ExceptionType.TIME_OUT.getCode() , message);
	}

}
