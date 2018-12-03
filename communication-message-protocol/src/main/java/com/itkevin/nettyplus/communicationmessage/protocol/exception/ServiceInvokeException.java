package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      ServiceInvokeException
  * @Description:    服务调用异常
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:16
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:16
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class ServiceInvokeException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2710640874887323375L;

	public ServiceInvokeException() {
		super(ExceptionType.SERVICE_INVOKE_EXCEPTION);
	}

	public ServiceInvokeException(String message) {
		super(ExceptionType.SERVICE_INVOKE_EXCEPTION.getCode() , message);
	}

}
