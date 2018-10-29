package com.itkevin.nettyplus.springbootnettytest.server;

import com.itkevin.nettyplus.clientcommunication.core.ClientBootstrap;
import com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig;
import com.itkevin.nettyplus.clientcommunication.core.session.ISessionConnectionListener;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionConnectionEvent;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionException;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionFactory;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.DeviceStatus;
import com.itkevin.nettyplus.nettycommunication.core.session.DeviceAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TestBootstrap {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestBootstrap.class);

	public static void main(String[] args) throws InterruptedException, IOException {
		String deviceId = "10001";
		
		SocketClientConfig config = new SocketClientConfig();
		config.setDeviceId(deviceId);
		config.setBasePackages("com.tigerjoys.communication.client.test.command");
		config.setServerHost("127.0.0.1");
		config.setServerPort(9527);
		config.setIdleHeartTimeout(20);
		
		System.out.println("连接服务端开启");
		ClientBootstrap client = new ClientBootstrap(config);
		client.addSessionConnectionListener(new ISessionConnectionListener() {
			
			@Override
			public boolean onConnection(SessionConnectionEvent event) {
				System.out.println("与服务器第一次建立了Session连接通道！！！！");
				return true;
			}
		});
		
		client.start();
		
		Thread.sleep(10000L);

		ApplicationContext ctx = SpringApplication.run(TestBootstrap.class, args);
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		for (String profile : activeProfiles) {
			LOGGER.warn("Spring Boot 使用profile为:{}", profile);
		}
		
		
		Thread.sleep(5000);
		
		Map<String , Object> jsonMap = new HashMap<>();
		jsonMap.put("aa", "123");
		jsonMap.put("b", 999);
		
		try {
			SessionFactory.getSession().setDeviceStatusNotifyServer(DeviceStatus.BUSY);
		} catch (SessionException e) {
			e.printStackTrace();
		}
		
		Thread.sleep(5000);
		
		System.out.println("打印服务端和客户端的终端状态");
		System.out.println("客户端终端状态：" + SessionFactory.getSession().getDeviceStatus().name());
		System.out.println("服务端终端状态：" + DeviceAgent.getDeviceStatus(deviceId).name());
		
		
		/*IServiceRemote serviceRemote = Global.getInstance().resolve(IServiceRemote.class);
		
		Future f = serviceRemote.sayFuture("OOO", 111);
		long s = System.currentTimeMillis();
		try {
			JSONArray jsonArray = f.get(JSONArray.class);
			for(Object o : jsonArray) {
				System.out.println(o);
			}
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
		long e = System.currentTimeMillis();
		System.out.println("总计花费：" + (e - s) + "毫秒!");*/
		
		/*List<String> list = serviceRemote.sayHello("ABC", 100);
		printList(list);
		
		list = serviceRemote.sayWorld(JsonHelper.toJson(jsonMap));
		printList(list);
		
		serviceRemote.sayVoid("BCD", 101);
		ResponseMessage response = serviceRemote.sayMessage("DEF", 999);
		System.out.println("返回数据：" + response.getBody());*/
		
		/*List<String> list = serviceRemote.sayAsyncHello(o -> {
			System.out.println(o.getReturnObject());
			
			System.out.println("我这个是异步回调，但是框架层还没有实现1");
			return true;
		} , "HIO", 888 , o -> {
			System.out.println(o.getReturnObject());
			
			System.out.println("我这个是异步回调，但是框架层还没有实现2");
			return true;
		});
		printList(list);*/
		
		/*List<String> list = serviceRemote.sayAsyncHello(new IAsyncCallBackListener() {

			@Override
			public boolean callback(CallBackEvent returnObject) {
				System.out.println("我这个是异步回调，但是框架层还没有实现1");
				return true;
			}
			
		} , "HIO", 888 , new IAsyncCallBackListener() {

			@Override
			public boolean callback(CallBackEvent returnObject) {
				System.out.println("我这个是异步回调，但是框架层还没有实现2");
				return true;
			}
			
		});
		printList(list);*/
		
		/*List<String> arr = new ArrayList<>();
		arr.add("a");
		arr.add("b");
		arr.add("c");
		
		RequestMessage request = new RequestMessage(ClientConstant.DEVICE_ID , "/api/test/123");
		request.setMessageTime(System.currentTimeMillis());
		request.setBody(JsonHelper.toJson(arr));
		
		Protocol p = new Protocol(0, MessageFromType.CLIENT, request);
		
		ISession session = SessionFactory.getSession();
		try {
			p = session.request(p);
			System.out.println("返回数据结果：" + JsonHelper.toJson(p.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		System.in.read();
	}
	
	private static void printList(List<String> list) {
		System.out.println("STR=================STR");
		if(list != null && !list.isEmpty()) {
			list.forEach(System.out :: println);
		} else {
			System.out.println("EMPTY");
		}
		System.out.println("END=================END");
	}
	
}
