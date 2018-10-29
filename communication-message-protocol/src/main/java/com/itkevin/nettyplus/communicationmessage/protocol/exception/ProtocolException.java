package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class ProtocolException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2629327321768097883L;

	public ProtocolException() {
		super(ExceptionType.PROTOCOL);
	}

	public ProtocolException(String message) {
		super(ExceptionType.PROTOCOL.getCode() , message);
	}

}
