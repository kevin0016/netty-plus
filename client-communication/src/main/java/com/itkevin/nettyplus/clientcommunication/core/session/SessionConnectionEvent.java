package com.itkevin.nettyplus.clientcommunication.core.session;


import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;

/**
 * Session连接事件对象
 * @author chengang
 *
 */
public final class SessionConnectionEvent {
	
	/**
	 * 心跳消息
	 */
	private IMessage message;
	
	/**
	 * Session
	 */
	private ISession session;
	
	public SessionConnectionEvent(IMessage message , ISession session) {
		this.message = message;
		this.session = session;
	}

	public IMessage getMessage() {
		return message;
	}

	public void setMessage(IMessage message) {
		this.message = message;
	}

	public ISession getSession() {
		return session;
	}

	public void setSession(ISession session) {
		this.session = session;
	}

}
