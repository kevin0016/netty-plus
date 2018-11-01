package com.itkevin.nettyplus.communicationmessage.protocol.exception;

/**     
  *
  * @ClassName:      ThrowErrorHelper
  * @Description:    错误辅助类
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:20
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:20
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public final class ThrowErrorHelper {
	
	/**
	 * 创建服务调用异常类对象
	 * @param errCode - 异常错误编号
	 * @param exception - 异常信息
	 * @return RemoteException
	 */
	public static RemoteException throwServiceError(int errCode, String exception) {
		ExceptionType exType = ExceptionType.getByCode(errCode);
		if(exType == null) {
			return new RemoteException(exception);
		}
		
		try {
			//通过反射创建异常类实例
			return (RemoteException)exType.getExceptionClass().getConstructor(String.class).newInstance(exception);
		} catch (Exception e) {
			return new RemoteException(exception);
		}
	}

}
