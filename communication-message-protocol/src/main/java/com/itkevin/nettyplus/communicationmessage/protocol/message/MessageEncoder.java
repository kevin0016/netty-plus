package com.itkevin.nettyplus.communicationmessage.protocol.message;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.ProtocolConst;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MessageEncoder extends MessageToByteEncoder<Protocol> {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(MessageEncoder.class);
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Protocol p, ByteBuf out) throws Exception {
		out.writeBytes(ProtocolConst.P_START_TAG);
		out.writeBytes(p.toBytes());
		out.writeBytes(ProtocolConst.P_END_TAG);
	}
	
}
