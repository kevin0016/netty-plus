package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

public class RequestMessage extends DefaultMessage {
	
	private String mapping;
	
	private String body;
	
	public RequestMessage() {
		
	}
	
	public RequestMessage(String deviceId , String mapping) {
		this.deviceId = deviceId;
		this.mapping = mapping;
	}
	
	@Override
	public MessageType messageType() {
		return MessageType.Request;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
