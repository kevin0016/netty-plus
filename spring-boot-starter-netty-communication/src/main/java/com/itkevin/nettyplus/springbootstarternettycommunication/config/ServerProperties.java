package com.itkevin.nettyplus.springbootstarternettycommunication.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * application.yml中的netty服务配置信息
 * @author chengang
 *
 */
@ConfigurationProperties(prefix = "socket.server")
public class ServerProperties {
	
	/**
	 * 读空闲时间设定，单位(秒)
	 */
	private int readerIdleTime = 20;
	
	/**
	 * 端口号
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

	public int getReaderIdleTime() {
		return readerIdleTime;
	}

	public void setReaderIdleTime(int readerIdleTime) {
		this.readerIdleTime = readerIdleTime;
	}

	public long getSlowMethodMillis() {
		return slowMethodMillis;
	}

	public void setSlowMethodMillis(long slowMethodMillis) {
		this.slowMethodMillis = slowMethodMillis;
	}

	public boolean isSlowMethod() {
		return slowMethod;
	}

	public void setSlowMethod(boolean slowMethod) {
		this.slowMethod = slowMethod;
	}

}
