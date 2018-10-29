package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class ServiceInvokeException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710640874887323375L;

	public ServiceInvokeException() {
		super(ExceptionType.SERVICE_INVOKE_EXCEPTION);
	}

	public ServiceInvokeException(String message) {
		super(ExceptionType.SERVICE_INVOKE_EXCEPTION.getCode() , message);
	}

}
