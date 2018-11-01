package com.itkevin.nettyplus.nettycommunication.core.config;

import com.itkevin.nettyplus.nettycommunication.core.hotkey.IProxyFactory;

/**     
  *
  * @ClassName:      SocketServerConfig
  * @Description:    服务器端配置参数
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:25
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:25
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public final class SocketServerConfig {
	
	/**
	 * 读空闲超时时间设定(秒)，如果channelRead()方法超过readerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法；
	 */
	private int readerIdleTime = 20;
	
	/**
	 * 默认开启端口
	 */
	private int port;
	
	/**
	 * 默认扫描的包路径
	 */
	private String basePackages;
	
	/**
	 * 是否打印慢方法
	 */
	private boolean slowMethod = false;
	
	/**
	 * 慢方法的耗时阈值，单位(毫秒)
	 */
	private long slowMethodMillis = 1000L;
	
	/**
	 * 默认的代理类工厂
	 */
	private IProxyFactory proxyFactory;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(String basePackages) {
		this.basePackages = basePackages;
	}

	public IProxyFactory getProxyFactory() {
		return proxyFactory;
	}

	public void setProxyFactory(IProxyFactory proxyFactory) {
		this.proxyFactory = proxyFactory;
	}

	public int getReaderIdleTime() {
		return readerIdleTime;
	}

	public void setReaderIdleTime(int readerIdleTime) {
		if(readerIdleTime <= 0) {
			throw new IllegalArgumentException("readerIdleTime Must be greater than zero!");
		}
		this.readerIdleTime = readerIdleTime;
	}

	public long getSlowMethodMillis() {
		return slowMethodMillis;
	}

	public void setSlowMethodMillis(long slowMethodMillis) {
		if(slowMethodMillis < 0) {
			throw new IllegalArgumentException("slowMethodMillis Must be greater than zero!");
		}
		this.slowMethodMillis = slowMethodMillis;
	}

	public boolean isSlowMethod() {
		return slowMethod;
	}

	public void setSlowMethod(boolean slowMethod) {
		this.slowMethod = slowMethod;
	}

}
