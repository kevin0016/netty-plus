package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      RebootException
  * @Description:    服务重启
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:15
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:15
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class RebootException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4327450650738132374L;

	public RebootException() {
		super(ExceptionType.REBOOT_EXCEPTION);
	}

	public RebootException(String message) {
		super(ExceptionType.REBOOT_EXCEPTION.getCode() , message);
	}

}
