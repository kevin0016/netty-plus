package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      DataOverFlowException
  * @Description:    数据溢出
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:12
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:12
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class DataOverFlowException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 38751782790059967L;

	public DataOverFlowException() {
		super(ExceptionType.DATA_OVER_FLOW_EXCEPTION);
	}

	public DataOverFlowException(String message) {
		super(ExceptionType.DATA_OVER_FLOW_EXCEPTION.getCode() , message);
	}

}
