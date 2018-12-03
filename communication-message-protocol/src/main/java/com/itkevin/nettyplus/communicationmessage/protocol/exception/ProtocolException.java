package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      ProtocolException
  * @Description:    协议错误
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:15
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:15
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
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
