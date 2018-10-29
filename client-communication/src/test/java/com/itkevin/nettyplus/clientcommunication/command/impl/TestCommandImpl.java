package com.itkevin.nettyplus.clientcommunication.command.impl;

import com.google.inject.Inject;
import com.itkevin.nettyplus.clientcommunication.command.IServiceRemote;
import com.itkevin.nettyplus.clientcommunication.command.ITestCommand;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.ProxyModule;

@ProxyModule(from=ITestCommand.class)
public class TestCommandImpl implements ITestCommand {
	
	@Inject
	private IServiceRemote serviceRemote;
	@Override
	public void sayHello() {
		System.out.println(serviceRemote.sayHello("abc" , 101));
	}

	@Override
	public String getTask(String json) {
		return serviceRemote.getTask(json);
	}

}
