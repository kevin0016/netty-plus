package com.itkevin.nettyplus.communicationmessage.protocol.enums;


import com.itkevin.nettyplus.communicationmessage.protocol.serialize.ISerialize;
import com.itkevin.nettyplus.communicationmessage.protocol.serialize.JSONSerialize;

/**     
  *
  * @ClassName:      SerializeType
  * @Description:    序列化枚举
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:16
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:16
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public enum SerializeType {

	/**
	 * json
	 */
	JSON(1 , new JSONSerialize()),
	
	/**
	 * java
	 */
	JAVABinary(2 , null),
	
	/**
	 * xml
	 */
	XML(3 , null);

	/**
	 * 序列化编码
	 */
	private final int code;
	
	/**
	 * 序列化对应的对象实体
	 */
	private final ISerialize serialize;
	
	private SerializeType(int code , ISerialize serialize) {
		this.code = code;
		this.serialize = serialize;
	}

	public int getCode() {
		return this.code;
	}

	public ISerialize getSerialize() {
		return serialize;
	}

	public static SerializeType getSerializeType(int code) {
		for (SerializeType type : values()) {
			if (type.code == code) {
				return type;
			}
		}
		return null;
	}

}
