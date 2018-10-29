package com.itkevin.nettyplus.nettycommunication.test;

import com.itkevin.nettyplus.nettycommunication.core.NettyBootstrap;
import com.itkevin.nettyplus.nettycommunication.core.config.SocketServerConfig;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.DefaultProxyFactory;

public class ServerClient {

	public static void main(String[] args) throws InterruptedException {
		//初始化配置信息
		SocketServerConfig serviceConfig = new SocketServerConfig();
		serviceConfig.setBasePackages("com.tigerjoys.onion.communication.server.command");
		serviceConfig.setPort(9527);
		serviceConfig.setProxyFactory(new DefaultProxyFactory());
		
		NettyBootstrap boostrap = new NettyBootstrap(serviceConfig);
		boostrap.start();
	}

}
