package com.itkevin.nettyplus.clientcommunication.core.server;

import com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig;
import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.clientcommunication.core.message.IMessageProcessor;
import com.itkevin.nettyplus.communicationmessage.protocol.ProtocolConst;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.MessageDecoder;
import com.itkevin.nettyplus.communicationmessage.protocol.message.MessageEncoder;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.ProtocolHelper;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public final class SocketServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);
	
	private EventLoopGroup eventLoopGroup;
	
	private SocketClientConfig config;
	
	private Bootstrap bootstrap;
	
	private volatile boolean reConnection = false;
	
	public SocketServer() {
		this.eventLoopGroup = new NioEventLoopGroup();
		this.config = Global.getInstance().getClientConfig();
	}
	
	public void start() throws InterruptedException {
		LOGGER.info("----------------start socket server--------------------");
		initServer();
		LOGGER.info("----------------socket server start finish-------------");
	}
	
	private void initServer() throws InterruptedException {
		bootstrap = new Bootstrap(); // 客户端引导类
        bootstrap.group(this.eventLoopGroup);// 多线程处理
        bootstrap.channel(NioSocketChannel.class);// 指定通道类型为NioServerSocketChannel，一种异步模式，OIO阻塞模式为OioServerSocketChannel
        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);//禁用Nagle算法
        bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000);//连接超时
        bootstrap.option(ChannelOption.SO_TIMEOUT, 5000);
        bootstrap.remoteAddress(new InetSocketAddress(config.getServerHost(), config.getServerPort()));// 指定请求地址
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) throws Exception {
            	ChannelPipeline pipeline = ch.pipeline();
            	
            	IMessageProcessor messageProcessor = new SocketMessageProcessor();
            	
            	pipeline.addLast(new DelimiterBasedFrameDecoder(ProtocolConst.MAX_FRAME_LENGTH, true , Unpooled.copiedBuffer(ProtocolConst.P_END_TAG)));
            	pipeline.addLast(new MessageDecoder());
            	pipeline.addLast(new MessageEncoder());
            	pipeline.addLast(new ConnectionWatchdog(SocketServer.this));
            	pipeline.addLast(new IdleStateHandler(0, config.getWriterIdleTime(), 0, TimeUnit.SECONDS));//每隔30s的时间触发一次userEventTriggered的方法，并且指定IdleState的状态位是WRITER_IDLE
            	pipeline.addLast(new ClientHeartBeatHandler());//实现userEventTriggered方法，并在state是WRITER_IDLE的时候发送一个心跳包到sever端，告诉server端我还活着
            	pipeline.addLast(new ClientMessageHandler(messageProcessor));
            }
        });
        
        ChannelFuture f = bootstrap.connect();
        f.addListener(new ChannelFutureListener() {

			@Override
			public void operationComplete(ChannelFuture future) throws Exception {
				LOGGER.info("connection server status : " + future.isDone()+",success:" + future.isSuccess());

				reConnection = false;
				if (future.isDone() && future.isSuccess()) {
					LOGGER.info("---- connection success-------");
					//服务器连接成功，立马发送一条心跳消息
					future.channel().writeAndFlush(ProtocolHelper.createHeartBeatMessage(MessageFromType.CLIENT , config.getDeviceId() , true));
				} else {
					future.cause().printStackTrace();
					try {
						f.channel().close();
					} catch (Exception e) {
						LOGGER.info("=================");
					}
					LOGGER.info("---- connection fail," + config.getReconnectTime() + " second later reconnection ---------host="+config.getServerHost()+",port=" + config.getServerPort());
					reconnection();
				}
			}
        	
        });
	}
	
	/**
	 * 重新连接，暂停的时间
	 *
	 * @throws InterruptedException
	 */
	public synchronized void reconnection() throws InterruptedException {
		if(reConnection) {
			return;
		}
		reConnection = true;
		this.eventLoopGroup.schedule(new Runnable() {
            @Override
            public void run() {
            	try {
            		initServer();
				} catch (InterruptedException e) {
					LOGGER.error(e.getMessage() , e);
				}
            }

        }, config.getReconnectTime(), TimeUnit.SECONDS);
	}

}
