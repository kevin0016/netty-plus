package com.itkevin.nettyplus.nettycommunication.core.tcp;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.server.IMessageProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * server handler
 * @author chengang
 *
 */
public class SocketServerHandler extends SimpleChannelInboundHandler<Protocol> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SocketServerHandler.class);
	
	private IMessageProcessor messageProcessor;
	
	public SocketServerHandler(IMessageProcessor messageProcessor) {
		this.messageProcessor = messageProcessor;
	}
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol p) throws Exception {
		LOGGER.info("Client say : " + FastJsonHelper.toJson(p.getEntity()));
		
		MessageType mt = p.getMessageType();
		switch(mt) {
			case Request:
				//TODO 此处是否需要检查合法性？？
				messageProcessor.processReceiveMessage(ctx, p);
				break;
			case Response:
				LOGGER.warn("this is client response!");
				break;
			case Exception:
				LOGGER.warn("this is client exception!");
				break;
			case Status:
				messageProcessor.processStatusMessage(ctx, (StatusMessage)p.getEntity());
				break;
			default:
				LOGGER.warn("have not match messageType");
				break;
		}
    }

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause instanceof CorruptedFrameException) {
			// something goes bad with decoding
			LOGGER.warn("Error decoding a packet, probably a bad formatted packet, message: " + cause.getMessage());
		} else {
			LOGGER.error("Ugly error on networking", cause);
		}
		this.channelInactive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		messageProcessor.processReceiveDisconnect(ctx);
	}

}
