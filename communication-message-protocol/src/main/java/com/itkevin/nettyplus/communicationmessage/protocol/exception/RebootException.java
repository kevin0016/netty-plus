package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class RebootException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4327450650738132374L;

	public RebootException() {
		super(ExceptionType.REBOOT_EXCEPTION);
	}

	public RebootException(String message) {
		super(ExceptionType.REBOOT_EXCEPTION.getCode() , message);
	}

}
