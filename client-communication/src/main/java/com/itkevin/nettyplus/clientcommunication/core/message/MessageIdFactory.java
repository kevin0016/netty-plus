package com.itkevin.nettyplus.clientcommunication.core.message;

import com.itkevin.nettyplus.clientcommunication.core.config.ClientConstant;

public final class MessageIdFactory {
	
	private static int SESSION_ID = 1;
	
	/**
	 * 创建MessageId
	 * @return int
	 */
	public static int createMessageId() {
		synchronized (MessageIdFactory.class) {
			if (SESSION_ID > ClientConstant.MAX_MESSAGE_ID) {
				SESSION_ID = 1;
			}
			return SESSION_ID++;
		}
	}

}
