package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import java.util.Map;

/**
 * 代理对象工厂
 * @author chengang
 *
 */
public interface IProxyFactory {
	
	/**
	 * 初始化命令
	 * @param commandMap - Map<String, CommandInfo>
	 */
	public void init(Map<String, CommandInfo> commandMap);
	
	/**
	 * 获得一个代理
	 * @param mapping - 获得一个指定的代理
	 * @return IProxyStub
	 */
	public IProxyStub getProxy(String mapping);

}
