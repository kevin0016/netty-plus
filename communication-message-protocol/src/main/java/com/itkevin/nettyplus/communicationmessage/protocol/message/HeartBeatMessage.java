package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;

/**
 * @ProjectName: netty-plus
 * @Package: com.itkevin.nettyplus.communicationmessage.protocol.message
 * @ClassName: HeartBeatMessage
 * @Description: 异常消息体
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午5:47
 * @UpdateUser: Kevin
 * @UpdateDate: 18/11/1 下午5:47
 * @UpdateRemark: 更新日志
 * @Version: 1.0
 */
public class HeartBeatMessage extends DefaultMessage {
	
	/**
	 * 是否第一次请求,1是，0否
	 */
	private int fstConn;
	
	public HeartBeatMessage() {
		
	}
	
	public HeartBeatMessage(String deviceId) {
		this.deviceId = deviceId;
	}
	
	@Override
	public MessageType messageType() {
		return MessageType.HeartBeat;
	}

	public int getFstConn() {
		return fstConn;
	}

	public void setFstConn(int fstConn) {
		this.fstConn = fstConn;
	}

}
