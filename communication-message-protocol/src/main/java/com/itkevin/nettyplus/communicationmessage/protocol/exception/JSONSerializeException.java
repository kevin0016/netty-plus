package com.itkevin.nettyplus.communicationmessage.protocol.exception;
/**
  *
  * @ClassName:      JSONSerializeException
  * @Description:    JSON序列化出错
  * @Author:         Kevin
  * @CreateDate:     18/12/3 下午3:13
  * @UpdateUser:
  * @UpdateDate:     18/12/3 下午3:13
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class JSONSerializeException extends RemoteException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6585159057755382478L;

	public JSONSerializeException() {
		super(ExceptionType.JSON_SERIALIZE_EXCEPTION);
	}

	public JSONSerializeException(String message) {
		super(ExceptionType.JSON_SERIALIZE_EXCEPTION.getCode() , message);
	}

}
