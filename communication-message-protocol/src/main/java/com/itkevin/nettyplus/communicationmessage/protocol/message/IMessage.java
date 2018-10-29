package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

public interface IMessage {
	
	/**
	 * 消息的时间
	 */
	public long getMessageTime();
	
	/**
	 * 获得设备ID
	 * @return String
	 */
	public String getDeviceId();
	
	/**
	 * 消息类型
	 * @return MessageType
	 */
	public MessageType messageType();

}
