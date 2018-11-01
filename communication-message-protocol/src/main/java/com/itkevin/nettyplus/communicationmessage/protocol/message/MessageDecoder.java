package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.ProtocolConst;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.ProtocolHelper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
/**     
  *
  * @ClassName:      MessageDecoder
  * @Description:    数据加密
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:05
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:05
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public class MessageDecoder extends ByteToMessageDecoder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageDecoder.class);
	
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		LOGGER.debug("message receive");
		int totalLength = in.readableBytes();
		LOGGER.debug("reciveByte.length:" + totalLength);
		
		byte[] headDelimiter = new byte[ProtocolConst.P_START_TAG.length];
		in.readBytes(headDelimiter);
		
		if(ProtocolHelper.checkHeadDelimiter(headDelimiter)) {
			//读取所有信息
			byte[] requestBuffer = new byte[totalLength - ProtocolConst.P_START_TAG.length];
			in.readBytes(requestBuffer);
			
			Protocol p = new Protocol();
			p.fromBytes(requestBuffer);
			
			out.add(p);
		} else {
			LOGGER.error("protocol error: protocol head not match");
		}
	}
	
}