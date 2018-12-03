package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      NetException
  * @Description:    网络连接异常
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:13
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:13
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
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
