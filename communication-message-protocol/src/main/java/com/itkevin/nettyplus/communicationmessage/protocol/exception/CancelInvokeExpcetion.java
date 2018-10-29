package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class CancelInvokeExpcetion extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4400770616566227195L;

	public CancelInvokeExpcetion() {
		super(ExceptionType.REBOOT_EXCEPTION);
	}

	public CancelInvokeExpcetion(String message) {
		super(ExceptionType.REBOOT_EXCEPTION.getCode() , message);
	}

}
