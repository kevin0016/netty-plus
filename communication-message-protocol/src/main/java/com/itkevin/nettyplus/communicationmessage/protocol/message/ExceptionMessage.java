package com.itkevin.nettyplus.communicationmessage.protocol.message;


import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

/**
 * @ProjectName: netty-plus
 * @Package: com.itkevin.nettyplus.communicationmessage.protocol.message
 * @ClassName: ExceptionMessage
 * @Description: 异常消息
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午5:47
 * @UpdateUser: Kevin
 * @UpdateDate: 18/11/1 下午5:47
 * @UpdateRemark: 更新日志
 * @Version: 1.0
 */
public class ExceptionMessage extends DefaultMessage {
	
	/**
	 * 错误ID
	 */
	private int errCode;
	
	/**
	 * 错误信息
	 */
	private String errMsg;
	
	/**
	 * 错误来源IP
	 */
	private String fromIP;
	
	/**
	 * 错误目的IP
	 */
	private String toIP;
	
	public ExceptionMessage() {
		
	}
	
	public ExceptionMessage(int errCode) {
		this.errCode = errCode;
	}
	
	public ExceptionMessage(int errCode , String errMsg) {
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public ExceptionMessage(String deviceId , int errCode , String errMsg) {
		this.deviceId = deviceId;
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	@Override
	public MessageType messageType() {
		return MessageType.Exception;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String getFromIP() {
		return fromIP;
	}

	public void setFromIP(String fromIP) {
		this.fromIP = fromIP;
	}

	public String getToIP() {
		return toIP;
	}

	public void setToIP(String toIP) {
		this.toIP = toIP;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}

}
