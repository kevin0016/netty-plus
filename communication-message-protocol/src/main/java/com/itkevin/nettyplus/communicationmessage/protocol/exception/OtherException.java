package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**     
  *
  * @ClassName:      OtherException
  * @Description:    其它错误
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:14
  * @UpdateUser:     
  * @UpdateDate:     18/12/3 下午3:14
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class OtherException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 618798273496916633L;

	public OtherException() {
		super(ExceptionType.OTHER_EXCEPTION);
	}

	public OtherException(String message) {
		super(ExceptionType.OTHER_EXCEPTION.getCode() , message);
	}

}
