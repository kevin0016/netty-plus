package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class NotFoundMethodException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4927016392684667768L;

	public NotFoundMethodException() {
		super(ExceptionType.NOT_FOUND_METHOD_EXCEPTION);
	}

	public NotFoundMethodException(String message) {
		super(ExceptionType.NOT_FOUND_METHOD_EXCEPTION.getCode() , message);
	}

}
