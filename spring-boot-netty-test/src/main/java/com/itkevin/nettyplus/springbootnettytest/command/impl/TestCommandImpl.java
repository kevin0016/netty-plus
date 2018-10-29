package com.itkevin.nettyplus.springbootnettytest.command.impl;

import com.google.inject.Inject;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.ProxyModule;
import com.itkevin.nettyplus.springbootnettytest.command.IServiceRemote;
import com.itkevin.nettyplus.springbootnettytest.command.ITestCommand;

@ProxyModule(from=ITestCommand.class)
public class TestCommandImpl implements ITestCommand {
	
	@Inject
	private IServiceRemote serviceRemote;

	@Override
	public void sayHello() {
		System.out.println(serviceRemote.sayHello("abc" , 101));
	}

}
