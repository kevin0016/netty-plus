package com.itkevin.nettyplus.communicationmessage.protocol.enums;

/**
 * 消息来源枚举
 * @author chengang
 *
 */
public enum MessageFromType {
	
	UNKNOW(0),
	SERVER(1),
	CLIENT(2)
	;
	
	/**
	 * 编码
	 */
	private final int code;
	
	MessageFromType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	/**
	 * 根据消息来源编码获取枚举
	 * @param code - int
	 * @return MessageFromType
	 * @throws Exception
	 */
	public static MessageFromType getMessageFromType(int code) throws Exception {
		for (MessageFromType type : MessageFromType.values()) {
			if (type.code == code) {
				return type;
			}
		}
		return MessageFromType.UNKNOW;
	}

}
