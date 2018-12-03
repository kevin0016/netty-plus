package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      ParaException
  * @Description:    参数传递出错
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:14
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:14
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class ParaException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6245468023216635789L;

	public ParaException() {
		super(ExceptionType.PARA_EXCEPTION);
	}

	public ParaException(String message) {
		super(ExceptionType.PARA_EXCEPTION.getCode() , message);
	}

}
