package com.itkevin.nettyplus.nettycommunication.core.server;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.message.HeartBeatMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import io.netty.channel.ChannelHandlerContext;

/**
 * @ClassName: IMessageProcessor
 * @Description: 消息处理接口
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:04
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:04
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface IMessageProcessor {

    /**
     * 接收到心跳消息的处理
     *
     * @param ctx     - ChannelHandlerContext
     * @param message - 消息协议体
     */
    void processReceiveHeartMessage(ChannelHandlerContext ctx, HeartBeatMessage message);

    /**
     * 断开连接请求
     *
     * @param ctx - ChannelHandlerContext
     */
    void processReceiveDisconnect(ChannelHandlerContext ctx);

    /**
     * 接收消息
     *
     * @param ctx - ChannelHandlerContext
     * @param msg - 消息协议体
     */
    void processReceiveMessage(ChannelHandlerContext ctx, Protocol msg);

    /**
     * 接收状态消息
     *
     * @param ctx     - ChannelHandlerContext
     * @param message - 状态消息对象
     */
    void processStatusMessage(ChannelHandlerContext ctx, StatusMessage message);

}
