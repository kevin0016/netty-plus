package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class NetException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2981860571118797570L;

	public NetException() {
		super(ExceptionType.NET);
	}

	public NetException(String message) {
		super(ExceptionType.NET.getCode() , message);
	}

}
