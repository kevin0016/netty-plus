package com.itkevin.nettyplus.communicationmessage.protocol.exception;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**     
  *
  * @ClassName:      ServiceFrameException
  * @Description:    服务通信错误
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:20
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:20
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public class ServiceFrameException extends RemoteException {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceFrameException.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4365590114225450505L;
	
	/**
	 * 消息ID
	 */
	private final int messageId;
	
	/**
	 * 设备ID
	 */
	private final String deviceId;

	/**
	 * 错误类型枚举
	 */
	private final ExceptionType exType;
	
	/**
	 * 错误描述
	 */
	private final String errorMsg;
	
	/**
	 * 错误来源ID
	 */
	private final String fromIP;
	
	/**
	 * 错误返回ID
	 */
	private final String toIP;
	
	/**
	 * MessageRequest对象
	 */
	private final RequestMessage request;
	
	public ServiceFrameException(String errorMsg, int messageId , String deviceId , String fromIP, String toIP, RequestMessage request, ExceptionType exType, Throwable cause) {
		super(cause.getMessage() , cause);
		this.setErrCode(exType.getCode());
		this.messageId = messageId;
		this.deviceId = deviceId;
		this.exType = exType;
		this.errorMsg = errorMsg;
		this.fromIP = fromIP;
		this.toIP = toIP;
		this.request = request;
	}
	
	public ServiceFrameException(String errorMsg, int messageId , String deviceId, ExceptionType exType, Throwable cause){
	        this(errorMsg, messageId , deviceId, "", "", null, exType, cause);
	}
	
	public ServiceFrameException(String errorMsg, int messageId , String deviceId, ExceptionType exType){
		this(errorMsg, messageId , deviceId , "", "", null, exType, null);
	}
	
	public ServiceFrameException(int messageId , String deviceId , ExceptionType exType, Throwable cause){
		this("", messageId , deviceId, "", "", null, exType, cause);
	}
	
	public ServiceFrameException(int messageId , String deviceId, ExceptionType state){
		this("" , messageId , deviceId, "", "", null, state, null);
	}

	@Override
	public void printStackTrace() {
		LOGGER.info("-------------------------begin-----------------------------");
		LOGGER.info("fromIP:" + this.fromIP);
		LOGGER.info("toIP:" + this.toIP);
		LOGGER.info("exType:" + this.exType.getErrorMsg());
		LOGGER.info("errorMsg:" + this.errorMsg);
		LOGGER.info("MessageBodyBase:");
		LOGGER.info("Server.mapping:" + request.getMapping());
		LOGGER.info("Server.DeviceId:" + request.getDeviceId());
		LOGGER.info("Server.Body:" + request.getBody());
		
		super.printStackTrace();
		
		LOGGER.info("--------------------------end------------------------------");
	}

	public ExceptionType getExType() {
		return exType;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getFromIP() {
		return fromIP;
	}

	public String getToIP() {
		return toIP;
	}

	public RequestMessage getRequest() {
		return request;
	}

	public int getMessageId() {
		return messageId;
	}

	public String getDeviceId() {
		return deviceId;
	}

}
