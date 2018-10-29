package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class DBException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755953381689748732L;

	public DBException() {
		super(ExceptionType.DB);
	}

	public DBException(String message) {
		super(ExceptionType.DB.getCode() , message);
	}

}
