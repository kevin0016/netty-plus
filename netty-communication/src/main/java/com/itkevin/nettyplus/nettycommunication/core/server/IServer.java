package com.itkevin.nettyplus.nettycommunication.core.server;


import com.itkevin.nettyplus.nettycommunication.core.context.ServerType;

/**
 * 服务器接口
 * @author chengang
 *
 */
public interface IServer {
	
	/**
	 * 开启服务器
	 * @throws Exception
	 */
	public abstract void start() throws Exception;
	
	/**
	 * 关闭服务器
	 * @throws Exception
	 */
	public abstract void stop() throws Exception;
	
	/**
	 * 服务类型
	 * @return ServerType
	 */
	public abstract ServerType getServerType();

}
