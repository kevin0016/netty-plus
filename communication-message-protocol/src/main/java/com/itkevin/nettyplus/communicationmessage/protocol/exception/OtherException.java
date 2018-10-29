package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class OtherException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 618798273496916633L;

	public OtherException() {
		super(ExceptionType.OTHER_EXCEPTION);
	}

	public OtherException(String message) {
		super(ExceptionType.OTHER_EXCEPTION.getCode() , message);
	}

}
