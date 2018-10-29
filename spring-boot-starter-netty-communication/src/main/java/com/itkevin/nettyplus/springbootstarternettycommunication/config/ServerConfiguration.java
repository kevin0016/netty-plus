package com.itkevin.nettyplus.springbootstarternettycommunication.config;

import com.itkevin.nettyplus.nettycommunication.core.NettyBootstrap;
import com.itkevin.nettyplus.nettycommunication.core.config.SocketServerConfig;
import com.itkevin.nettyplus.springbootstarternettycommunication.hotkey.SpringProxyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * netty-communication的spring boot服务配置项
 * @author chengang
 *
 */
@Configuration
@EnableConfigurationProperties(ServerProperties.class)
@ConditionalOnProperty(prefix = "socket.server.enable", value = "true", matchIfMissing = true)
public class ServerConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServerConfiguration.class);
	
	private ApplicationContext context;
	
	@Autowired
	private ServerProperties serverProperties;
	
	public ServerConfiguration(ApplicationContext applicationContext, ServerProperties serverProperties) {
		this.context = applicationContext;
		this.serverProperties = serverProperties;
	}
	
	/**
	 * 创建SocketServer Bean
	 * @return SocketServer
	 * @throws Exception
	 */
	@Bean
    @ConditionalOnMissingBean(NettyBootstrap.class)
    public NettyBootstrap bootstrapNettyServer() throws Exception {
		LOGGER.info("basePackages : " + serverProperties.getBasePackages());
		LOGGER.info("port : " + serverProperties.getPort());
		
		if(serverProperties.getPort() == 0) {
			throw new RuntimeException("serverProperties port can't zero!");
		}
		if(StringUtils.isEmpty(serverProperties.getBasePackages())) {
			throw new RuntimeException("serverProperties basePackages can't empty!");
		}
		
		//初始化配置信息
		SocketServerConfig serviceConfig = new SocketServerConfig();
		serviceConfig.setBasePackages(serverProperties.getBasePackages());
		serviceConfig.setPort(serverProperties.getPort());
		serviceConfig.setProxyFactory( new SpringProxyFactory(context));
		serviceConfig.setReaderIdleTime(serverProperties.getReaderIdleTime());
		serviceConfig.setSlowMethod(serverProperties.isSlowMethod());
		serviceConfig.setSlowMethodMillis(serverProperties.getSlowMethodMillis());
		
		NettyBootstrap boostrap = new NettyBootstrap(serviceConfig);
		
		//必须通过一个线程来启动，否则springboot启动类会被卡住
		new Thread(() -> {
			try {
				boostrap.start();
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}).start();
		
		return boostrap;
    }

}
