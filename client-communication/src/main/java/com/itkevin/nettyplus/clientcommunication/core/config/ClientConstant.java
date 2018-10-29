package com.itkevin.nettyplus.clientcommunication.core.config;

/**
 * 客户端常量
 * @author chengang
 *
 */
public final class ClientConstant {
	
	/**
	 * DEX包路径
	 */
	public static final String DEX_PATH = "/data/local/tmp";
	
	/**
	 * DEX包解压路径
	 */
	public static final String CACHE_PATH = "/data/local/tmp/cache/";
	
	/**
	 * 最大的消息ID
	 */
	public static final long MAX_MESSAGE_ID = 1024 * 1024 * 1024;
	
	private ClientConstant() {
		
	}

}
