package com.itkevin.nettyplus.communicationmessage.protocol.message;


import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

public class ResponseMessage extends DefaultMessage {
	
	private String body;
	
	/**
	 * 终端时间/服务器时间
	 */
	private long terminalTime;
	
	public ResponseMessage() {
		
	}
	
	public ResponseMessage(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public MessageType messageType() {
		return MessageType.Response;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public long getTerminalTime() {
		return terminalTime;
	}

	public void setTerminalTime(long terminalTime) {
		this.terminalTime = terminalTime;
	}

}
