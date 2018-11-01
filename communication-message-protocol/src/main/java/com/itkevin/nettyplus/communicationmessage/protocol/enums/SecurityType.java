package com.itkevin.nettyplus.communicationmessage.protocol.enums;


import com.itkevin.nettyplus.communicationmessage.protocol.security.ISecurity;
import com.itkevin.nettyplus.communicationmessage.protocol.security.UnSecurity;

/**     
  *
  * @ClassName:      SecurityType
  * @Description:    加密类型枚举
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:15
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:15
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public enum SecurityType {
	
	/**
	 * 不加密
	 */
	UnSecurity(0 , new UnSecurity()),
	
	/**
	 * AES加/解密
	 */
	AES(1 , null),
	
	/**
	 * DES加/解密
	 */
	DES(2 , null);

	/**
	 * 加/解密编码
	 */
	private final int code;
	
	/**
	 * 加解密对应的对象实体
	 */
	private final ISecurity security;
	
	private SecurityType(int code , ISecurity security) {
		this.code = code;
		this.security = security;
	}

	public int getCode() {
		return code;
	}

	public ISecurity getSecurity() {
		return security;
	}

	public static SecurityType getSecurityType(int code) {
		for (SecurityType type : SecurityType.values()) {
			if (type.code == code) {
				return type;
			}
		}
		return null;
	}

}
