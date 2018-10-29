package com.itkevin.nettyplus.clientcommunication;

import com.itkevin.nettyplus.clientcommunication.command.ITestCommand;
import com.itkevin.nettyplus.clientcommunication.core.ClientBootstrap;
import com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig;
import com.itkevin.nettyplus.clientcommunication.core.context.Global;

import java.io.IOException;

public class ClientTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		SocketClientConfig config = new SocketClientConfig();
		config.setDeviceId("100001");
		config.setBasePackages("com.tigerjoys.communication.client.test.command");
		config.setServerHost("127.0.0.1");
		config.setServerPort(9527);
		config.setRequestTimeout(300);
		config.setWriterIdleTime(5);
		
		System.out.println("连接服务端开启");
		ClientBootstrap client = new ClientBootstrap(config);
		client.start();
		
		
		Thread.sleep(5000);
		
		while(true) {
			try {
				String json = "{\"deviceCode\":\"asdfsdfsd\",\"userUnique\":\"asdfsdfsd\"}";
				String result = Global.getInstance().resolve(ITestCommand.class).getTask(json);
				System.out.println(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(2000L);
		}
	}

}
