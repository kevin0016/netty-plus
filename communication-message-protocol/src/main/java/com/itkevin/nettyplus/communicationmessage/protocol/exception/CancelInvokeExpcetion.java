package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      CancelInvokeExpcetion
  * @Description:    取消调用
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:11
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:11
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class CancelInvokeExpcetion extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4400770616566227195L;

	public CancelInvokeExpcetion() {
		super(ExceptionType.REBOOT_EXCEPTION);
	}

	public CancelInvokeExpcetion(String message) {
		super(ExceptionType.REBOOT_EXCEPTION.getCode() , message);
	}

}
