package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class DataOverFlowException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 38751782790059967L;

	public DataOverFlowException() {
		super(ExceptionType.DATA_OVER_FLOW_EXCEPTION);
	}

	public DataOverFlowException(String message) {
		super(ExceptionType.DATA_OVER_FLOW_EXCEPTION.getCode() , message);
	}

}
