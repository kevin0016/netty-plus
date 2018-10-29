package com.itkevin.nettyplus.communicationmessage.protocol.message;

public abstract class DefaultMessage implements IMessage {
	
	protected String deviceId;
	
	protected long messageTime;
	
	@Override
	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Override
	public long getMessageTime() {
		return messageTime;
	}

	public void setMessageTime(long messageTime) {
		this.messageTime = messageTime;
	}

}
