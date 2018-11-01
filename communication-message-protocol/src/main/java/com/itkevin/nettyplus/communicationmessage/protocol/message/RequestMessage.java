package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
/**     
  *
  * @ClassName:      RequestMessage
  * @Description:    数据请求体
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:06
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:06
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public class RequestMessage extends DefaultMessage {
	
	private String mapping;
	
	private String body;
	
	public RequestMessage() {
		
	}
	
	public RequestMessage(String deviceId , String mapping) {
		this.deviceId = deviceId;
		this.mapping = mapping;
	}
	
	@Override
	public MessageType messageType() {
		return MessageType.Request;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
