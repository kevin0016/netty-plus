package com.itkevin.nettyplus.nettycommunication.core.config;

/**
 * 服务端配置常量
 * @author chengang
 *
 */
public final class ServerConstant {
	
	/**
	 * socket backlog
	 */
	public static final int DEFAULT_SOCKET_BACKLOG = 128;
	
	/**
	 * socket receive buffer
	 */
	public static final int DEFAULT_SOCKET_RCVBUF = 1024 * 64;
	
	/**
	 * socket send buffer
	 */
	public static final int DEFAULT_SOCKET_SNDBUF = 1024 * 64;
	
	/**
	 * 禁用Nagle算法
	 */
	public static final boolean DEFAULT_SOCKET_NODELAY = true;
	
	/**
	 * 是否启用心跳保活机制在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
	 */
	public static final boolean DEFAULT_SOCKET_KEEPALIVE = true;
	
	/**
	 * SessionManager 初始大小
	 */
	public static final int DEFAULT_SESSION_INIT_SIZE = 16;
	
	/**
	 * 监控间隔时间,5秒
	 */
	public static final long DEFAULT_MONITOR_INTERVAL_MILLS = 5000;
	
	/**
	 * 客户端超时时间,10秒
	 */
	public static final long DEFAULT_SESSION_TIMEOUT_MILLS = 10000;
	
	/**
	 * 业务线程池最小值
	 */
	public static final int DEFAULT_THREAD_POOL_MIN_SIZE = 255;
	
	/**
	 * 业务线程池最大值
	 */
	public static final int DEFAULT_THREAD_POOL_MAX_SIZE = 255;
	
	/**
	 * 业务线程最大接收的请求数
	 */
	public static final int DEFAULT_ACCEPT_COUNT = 2000;
	
	/**
	 * 业务在线程池中等待的最长超时时间
	 */
	public static final int DEFAULT_ACCEPT_WAIT_TIMEOUT = 10000;
	
	private ServerConstant() {
		
	}

}
