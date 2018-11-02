package com.itkevin.nettyplus.nettycommunication.core.session;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.DeviceStatus;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.HeartBeatMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.ProtocolHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.core.context.ServerType;
import com.itkevin.nettyplus.nettycommunication.core.server.IMessageProcessor;
import com.itkevin.nettyplus.nettycommunication.core.tcp.AsyncMessageInvoker;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SocketMessageProcessor
 * @Description: 消息处理
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:14
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:14
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class SocketMessageProcessor implements IMessageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketMessageProcessor.class);

    //private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("HB", CharsetUtil.UTF_8));

    @Override
    public void processReceiveHeartMessage(ChannelHandlerContext ctx, HeartBeatMessage msg) {
        Session session = SessionManager.getInstance().getSession(ctx);
        if (session == null) {//注册
            this.processReceiveConnect(ctx, msg);
        } else {//保持连接
            this.processReceivePing(ctx);
        }
    }

    /**
     * 处理心跳消息的连接请求
     *
     * @param ctx - ChannelHandlerContext
     * @param msg - IMessage
     */
    private void processReceiveConnect(ChannelHandlerContext ctx, HeartBeatMessage msg) {
        LOGGER.info("deviceId {} register!", msg.getDeviceId());

        Session session = SessionManager.getInstance().createSession(ctx, msg);
        //发送心跳回应
        ctx.writeAndFlush(ProtocolHelper.createHeartBeatMessage(MessageFromType.SERVER, session.getDeviceId(), true)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
    }

    /**
     * 处理心跳消息的ping请求
     *
     * @param ctx - ChannelHandlerContext
     */
    private void processReceivePing(ChannelHandlerContext ctx) {
        Session session = SessionManager.getInstance().getSession(ctx);

        if (session != null) {
            LOGGER.info("deviceId {} keep register!", session.getDeviceId());

            boolean keepAlive = SessionManager.getInstance().keepSession(ctx);
            if (!keepAlive) {
                processReceiveDisconnect(ctx);
            } else {
                ctx.writeAndFlush(ProtocolHelper.createHeartBeatMessage(MessageFromType.SERVER, session.getDeviceId(), false)).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            }
        }
    }

    @Override
    public void processReceiveDisconnect(ChannelHandlerContext ctx) {
        Session session = SessionManager.getInstance().getSession(ctx);
        if (session != null) {
            LOGGER.info("deviceId {} unregister!", session.getDeviceId());
            //清除session状态
            session.close();
        } else {
            ctx.close();
        }
    }

    @Override
    public void processReceiveMessage(ChannelHandlerContext ctx, Protocol p) {
        Session session = SessionManager.getInstance().getSession(ctx);

        if (session == null || session.isClosed()) {
            LOGGER.warn("publish message's session is null or closed");
            return;
        }
        //组装BeatContext
        AsyncMessageInvoker.getInstance().invoke(BeatContext.wrapContext(p, session, ServerType.TCP));
    }

    @Override
    public void processStatusMessage(ChannelHandlerContext ctx, StatusMessage message) {
        Session session = SessionManager.getInstance().getSession(ctx);

        if (session == null || session.isClosed()) {
            LOGGER.warn("status message's session is null or closed!");
            return;
        }

        DeviceStatus status = DeviceStatus.getDeviceState(message.getStatus());

        LOGGER.info("from client message - deviceId: " + session.getDeviceId() + ",current status " + session.getDeviceStatus().name() + ",change status : " + status.name());

        //修改设备状态
        session.setDeviceStatus(status);
    }

}
