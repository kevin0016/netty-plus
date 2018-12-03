package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      DBException
  * @Description:    数据库错误
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:12
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:12
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class DBException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5755953381689748732L;

	public DBException() {
		super(ExceptionType.DB);
	}

	public DBException(String message) {
		super(ExceptionType.DB.getCode() , message);
	}

}
