package com.itkevin.nettyplus.communicationmessage.protocol.exception;

public class JSONSerializeException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6585159057755382478L;

	public JSONSerializeException() {
		super(ExceptionType.JSON_SERIALIZE_EXCEPTION);
	}

	public JSONSerializeException(String message) {
		super(ExceptionType.JSON_SERIALIZE_EXCEPTION.getCode() , message);
	}

}
