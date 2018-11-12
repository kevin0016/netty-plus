package com.itkevin.nettyplus.nettycommunication.core.tcp;

import com.itkevin.nettyplus.communicationmessage.protocol.ProtocolConst;
import com.itkevin.nettyplus.communicationmessage.protocol.message.MessageDecoder;
import com.itkevin.nettyplus.communicationmessage.protocol.message.MessageEncoder;
import com.itkevin.nettyplus.nettycommunication.core.config.ServerConstant;
import com.itkevin.nettyplus.nettycommunication.core.context.Global;
import com.itkevin.nettyplus.nettycommunication.core.context.ServerType;
import com.itkevin.nettyplus.nettycommunication.core.server.IMessageProcessor;
import com.itkevin.nettyplus.nettycommunication.core.server.IServer;
import com.itkevin.nettyplus.nettycommunication.core.session.SocketMessageProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.concurrent.ImmediateEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName: SocketServer
 * @Description: 服务端
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:16
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:16
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class SocketServer implements IServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    static final ChannelGroup channelGroup = new DefaultChannelGroup(ImmediateEventExecutor.INSTANCE);

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public SocketServer() {
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
    }

    @Override
    public void start() throws Exception {
        LOGGER.info("----------------start socket server--------------------");
        initServer();
        LOGGER.info("----------------socket server start finish-------------");
    }

    /**
     * 初始化服务器
     */
    private void initServer() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            //采用主从模式，设置好boss线程和worker线程
            b.group(bossGroup, workerGroup)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)//指定NIO的模式
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            IMessageProcessor messageProcessor = new SocketMessageProcessor();

                            ChannelPipeline pipeline = ch.pipeline();

                            //处理心跳
                            pipeline.addLast(new IdleStateHandler(Global.getInstance().getServiceConfig().getReaderIdleTime(), 0, 0, TimeUnit.SECONDS));
                            pipeline.addLast(new DelimiterBasedFrameDecoder(ProtocolConst.MAX_FRAME_LENGTH, true, Unpooled.copiedBuffer(ProtocolConst.P_END_TAG)));
                            pipeline.addLast(new MessageDecoder());
                            pipeline.addLast(new MessageEncoder());
                            pipeline.addLast(new ServerHeartBeatHandler(messageProcessor));
                            pipeline.addLast(new SocketServerHandler(messageProcessor));
                        }

                    })
                    //针对主线程的配置
                    .option(ChannelOption.SO_BACKLOG, ServerConstant.DEFAULT_SOCKET_BACKLOG)
                    //针对子线程的配置
                    .childOption(ChannelOption.TCP_NODELAY, ServerConstant.DEFAULT_SOCKET_NODELAY)//禁用Nagle算法
                    .childOption(ChannelOption.SO_RCVBUF, ServerConstant.DEFAULT_SOCKET_RCVBUF)//socket server receive buffer size:1024 * 64
                    .childOption(ChannelOption.SO_SNDBUF, ServerConstant.DEFAULT_SOCKET_SNDBUF)//socket server send buffer size:1024 * 64
                    .childOption(ChannelOption.SO_KEEPALIVE, ServerConstant.DEFAULT_SOCKET_KEEPALIVE);

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(Global.getInstance().getServiceConfig().getPort()).syncUninterruptibly();

            Channel channel = f.channel();
            channelGroup.add(channel);

            // Wait until the server socket is closed.
            //channel.closeFuture().sync();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }/* finally {
			try {
				stop();
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}*/
    }

    @Override
    public void stop() throws Exception {
        //停止工作线程
        AsyncMessageInvoker.getInstance().stop();

        //停止所有通道
        LOGGER.info("----------------------------------------------------");
        LOGGER.info("-- socket server closing...");
        LOGGER.info("-- channels count : " + channelGroup.size());

        ChannelGroupFuture future = channelGroup.close();
        LOGGER.info("-- closing all channels...");
        future.awaitUninterruptibly();
        LOGGER.info("-- closed all channels...");

        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        LOGGER.info("SOCKET SERVER is closed.");
    }

    @Override
    public ServerType getServerType() {
        return ServerType.TCP;
    }

}
