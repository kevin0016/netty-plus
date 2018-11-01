package com.itkevin.nettyplus.communicationmessage.protocol.message;

/**
 * @ProjectName: netty-plus
 * @Package: com.itkevin.nettyplus.communicationmessage.protocol.message
 * @ClassName: DefaultMessage
 * @Description: 抽象消息体
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午5:47
 * @UpdateUser: Kevin
 * @UpdateDate: 18/11/1 下午5:47
 * @UpdateRemark: 更新日志
 * @Version: 1.0
 */
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
