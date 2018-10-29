package com.itkevin.nettyplus.clientcommunication.core.server;

import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.ProtocolHelper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class ClientHeartBeatHandler extends ChannelInboundHandlerAdapter {
	
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleState state = ((IdleStateEvent) evt).state();
			if (state == IdleState.WRITER_IDLE) {
				ctx.writeAndFlush(ProtocolHelper.createHeartBeatMessage(MessageFromType.CLIENT , Global.getInstance().getClientConfig().getDeviceId() , false));
			}
		} else {
			super.userEventTriggered(ctx, evt);
		}
	}
	
}
