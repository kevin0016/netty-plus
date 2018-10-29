package com.itkevin.nettyplus.communicationmessage.protocol.exception;

/**
 * 远程通信接口异常类
 * @author chengang
 *
 */
public class RemoteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 527649919065753649L;
	
	/**
	 * 错误码
	 */
	private int errCode;
	
	public RemoteException() {
		
	}
	
	public RemoteException(ExceptionType returnType) {
		super(returnType.getErrorMsg());
		this.errCode = returnType.getCode();
	}

	public RemoteException(String msg) {
		this(ExceptionType.OTHER_EXCEPTION.getCode(), msg);
	}
	
	public RemoteException(int errCode , String msg) {
		super(msg);
		this.errCode = errCode;
	}
	
	public RemoteException(String msg , Throwable cause) {
		super(msg , cause);
		this.errCode = ExceptionType.OTHER_EXCEPTION.getCode();
	}

	public int getErrCode() {
		return this.errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

}
