package com.itkevin.nettyplus.clientcommunication.core.server;

import com.itkevin.nettyplus.clientcommunication.core.message.IMessageProcessor;
import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.CorruptedFrameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * client message handler
 * @author chengang
 *
 */
public class ClientMessageHandler extends SimpleChannelInboundHandler<Protocol> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ClientMessageHandler.class);
	
	private IMessageProcessor messageProcessor;
	
	public ClientMessageHandler(IMessageProcessor messageProcessor) {
		this.messageProcessor = messageProcessor;
	}
	
	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Protocol p) throws Exception {
		MessageType mt = p.getMessageType();
		switch(mt) {
			case Request:
				messageProcessor.processReceiveRequestMessage(ctx, p);
				break;
			case Response:
				//TODO 此处是否需要检查合法性？？
				messageProcessor.processReceiveResponseMessage(ctx, p);
				break;
			case Exception:
				//输出异常
				messageProcessor.processReceiveExceptionMessage(ctx, p);
				break;
			case Status:
				messageProcessor.processReceiveStatusMessage(ctx, (StatusMessage)p.getEntity());
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
	}

}
