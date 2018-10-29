package com.itkevin.nettyplus.clientcommunication.core.config;

/**
 * 客户端配置
 * @author chengang
 *
 */
public final class SocketClientConfig {
	
	/**
	 * 设备编号
	 */
	private String deviceId;
	
	/**
	 * 服务地址
	 */
	private String serverHost;
	
	/**
	 * 服务端口
	 */
	private int serverPort;
	
	/**
	 * 需要扫描的代理包路径
	 */
	private String basePackages;
	
	/**
	 * 客户端写空闲超时时间设定(秒)。具体作用就是如果write()方法超过writerIdleTime时间未被调用则会触发超时事件调用userEventTrigger()方法（向服务端发送心跳检测）
	 */
	private int writerIdleTime = 5;
	
	/**
	 * 客户端请求超时时间，秒
	 */
	private long requestTimeout = 300;
	
	/**
	 * 客户端异步超时时间，秒
	 */
	private int asyncRequestTimeout = 20;
	
	/**
	 * 服务器连接断开后的重试间隔，单位(秒)
	 */
	private int reconnectTime = 5;
	
	/**
	 * 心跳空闲超时时间，单位(秒)
	 */
	private int idleHeartTimeout = 20;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getBasePackages() {
		return basePackages;
	}

	public void setBasePackages(String basePackages) {
		this.basePackages = basePackages;
	}

	public int getWriterIdleTime() {
		return writerIdleTime;
	}

	public void setWriterIdleTime(int writerIdleTime) {
		if(writerIdleTime <= 0) {
			throw new IllegalArgumentException("writerIdleTime Must be greater than zero!");
		}
		this.writerIdleTime = writerIdleTime;
	}

	public long getRequestTimeout() {
		return requestTimeout;
	}

	public void setRequestTimeout(long requestTimeout) {
		if(requestTimeout <= 0) {
			throw new IllegalArgumentException("requestTimeout Must be greater than zero!");
		}
		this.requestTimeout = requestTimeout;
	}

	public int getReconnectTime() {
		return reconnectTime;
	}

	public void setReconnectTime(int reconnectTime) {
		if(reconnectTime <= 0) {
			throw new IllegalArgumentException("reconnectTime Must be greater than zero!");
		}
		this.reconnectTime = reconnectTime;
	}

	public int getIdleHeartTimeout() {
		return idleHeartTimeout;
	}

	public void setIdleHeartTimeout(int idleHeartTimeout) {
		if(idleHeartTimeout <= 0) {
			throw new IllegalArgumentException("idleHeartTimeout Must be greater than zero!");
		}
		this.idleHeartTimeout = idleHeartTimeout;
	}

	public int getAsyncRequestTimeout() {
		return asyncRequestTimeout;
	}

	public void setAsyncRequestTimeout(int asyncRequestTimeout) {
		if(asyncRequestTimeout <= 0) {
			throw new IllegalArgumentException("asyncRequestTimeout Must be greater than zero!");
		}
		this.asyncRequestTimeout = asyncRequestTimeout;
	}

}
