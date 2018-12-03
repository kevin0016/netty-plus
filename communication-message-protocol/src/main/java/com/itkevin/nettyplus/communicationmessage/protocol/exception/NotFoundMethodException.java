package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      NotFoundMethodException
  * @Description:    没有找到要调用的方法
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:13
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:13
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class NotFoundMethodException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4927016392684667768L;

	public NotFoundMethodException() {
		super(ExceptionType.NOT_FOUND_METHOD_EXCEPTION);
	}

	public NotFoundMethodException(String message) {
		super(ExceptionType.NOT_FOUND_METHOD_EXCEPTION.getCode() , message);
	}

}
