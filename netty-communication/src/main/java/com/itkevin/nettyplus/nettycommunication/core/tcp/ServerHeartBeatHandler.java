package com.itkevin.nettyplus.nettycommunication.core.tcp;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.HeartBeatMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.core.context.Global;
import com.itkevin.nettyplus.nettycommunication.core.context.ServerType;
import com.itkevin.nettyplus.nettycommunication.core.filter.IFilter;
import com.itkevin.nettyplus.nettycommunication.core.server.IMessageProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ServerHeartBeatHandler
 * @Description: 服务器心跳处理
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:16
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:16
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class ServerHeartBeatHandler extends ChannelInboundHandlerAdapter {

    private final static Logger LOGGER = LoggerFactory.getLogger(ServerHeartBeatHandler.class);

    private IMessageProcessor messageProcessor;

    public ServerHeartBeatHandler(IMessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.READER_IDLE) {
                LOGGER.warn("Client is idle, close it.");
                messageProcessor.processReceiveDisconnect(ctx);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");

        BeatContext context = BeatContext.wrapNoSessionContext(ctx, ServerType.TCP);
        //连接进来后，查看是否在禁用IP中
        for (IFilter f : Global.getInstance().getConnectionFilterList()) {
            f.filter(context);
        }
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg != null) {
            Protocol p = (Protocol) msg;
            if (p.getMessageType() == MessageType.HeartBeat) {
                IMessage message = (IMessage) p.getEntity();

                LOGGER.info("read heartBeat from client {}", FastJsonHelper.toJson(message));

                //接收到心跳消息的处理逻辑
                messageProcessor.processReceiveHeartMessage(ctx, (HeartBeatMessage) message);
                return;
            }
            ctx.fireChannelRead(p);
        }
    }

}
