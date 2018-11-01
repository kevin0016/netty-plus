package com.itkevin.nettyplus.communicationmessage.protocol.enums;

/**     
  *
  * @ClassName:      DeviceStatus
  * @Description:    设备状态枚举
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:13
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:13
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public enum DeviceStatus {
	
	UNKNOWN(0),//未知
	IDLE(1),//空闲
	BUSY(2),//忙碌
	DOWN(9)//下线
	;
	
	private final int code;
	
	DeviceStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static DeviceStatus getDeviceState(int code) {
		for (DeviceStatus s : DeviceStatus.values()) {
			if (s.code == code) {
				return s;
			}
		}
		return UNKNOWN;
	}

}
