package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class ParaException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6245468023216635789L;

	public ParaException() {
		super(ExceptionType.PARA_EXCEPTION);
	}

	public ParaException(String message) {
		super(ExceptionType.PARA_EXCEPTION.getCode() , message);
	}

}
