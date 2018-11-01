package com.itkevin.nettyplus.communicationmessage.protocol.enums;

/**     
  *
  * @ClassName:      MessageFromType
  * @Description:    消息来源枚举
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:14
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:14
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
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
