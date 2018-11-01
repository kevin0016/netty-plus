package com.itkevin.nettyplus.communicationmessage.protocol.enums;

/**     
  *
  * @ClassName:      PlatformType
  * @Description:    平台类型枚举
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:14
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:14
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
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
