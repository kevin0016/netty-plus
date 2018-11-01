package com.itkevin.nettyplus.communicationmessage.protocol.exception;

/**     
  *
  * @ClassName:      TimeoutException
  * @Description:    客户端调用超时异常
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:20
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:20
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public class TimeoutException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4345935963121140739L;

	public TimeoutException() {
		super(ExceptionType.TIME_OUT);
	}

	public TimeoutException(String message) {
		super(ExceptionType.TIME_OUT.getCode() , message);
	}

}
