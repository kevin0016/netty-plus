package com.itkevin.nettyplus.communicationmessage.protocol.enums;

/**
 * 平台类型枚举
 * @author chengang
 *
 */
public enum PlatformType {

	Java(0),
	C(1);

	/**
	 * 平台类型编码
	 */
	private final int code;
	
	private PlatformType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PlatformType getPlatformType(int code) {
		for (PlatformType type : PlatformType.values()) {
			if (type.code == code) {
				return type;
			}
		}
		return null;
	}

}
