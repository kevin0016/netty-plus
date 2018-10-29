package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

public class StatusMessage extends DefaultMessage {
	
	/**
	 * 状态编号
	 */
	private int status;
	
	public StatusMessage() {
		
	}
	
	public StatusMessage(String deviceId , int status) {
		this.deviceId = deviceId;
		this.status = status;
	}

	@Override
	public MessageType messageType() {
		return MessageType.Status;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
