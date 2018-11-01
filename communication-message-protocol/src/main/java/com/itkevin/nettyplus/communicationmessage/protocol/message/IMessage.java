package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
/**     
  *
  * @ClassName:      IMessage
  * @Description:    数据获取接口
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:09
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:09
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public interface IMessage {
	
	/**
	 * 消息的时间
	 */
	public long getMessageTime();
	
	/**
	 * 获得设备ID
	 * @return String
	 */
	public String getDeviceId();
	
	/**
	 * 消息类型
	 * @return MessageType
	 */
	public MessageType messageType();

}
