package com.itkevin.nettyplus.clientcommunication.core.message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息等待处理类
 * @author chengang
 *
 */
public final class MessageWaitProcessor {
	
	private static final Map<Integer, WindowData> WAIT_WINDOWS = new ConcurrentHashMap<>();
	
	/**
	 * 注册一个messageId
	 * @param messageId
	 */
	public static void registerEvent(int messageId) {
		AutoReceiveEvent event = new AutoReceiveEvent();
		WindowData wd = new WindowData(messageId , event);
		WAIT_WINDOWS.put(messageId, wd);
	}
	
	/**
	 * 注册一个messageId
	 * @param messageId - int
	 * @param wd - WindowData
	 */
	public static void registerEvent(int messageId, WindowData wd) {
		WAIT_WINDOWS.put(messageId, wd);
	}
	
	/**
	 * 注销一个messageId
	 * @param messageId
	 */
	public static void unregisterEvent(int messageId) {
		WAIT_WINDOWS.remove(messageId);
	}
	
	/**
	 * 判断是否已经有了messageId了
	 * @param messageId - int
	 * @return boolean
	 */
	public static boolean hasMessageId(int messageId) {
		return WAIT_WINDOWS.containsKey(messageId);
	}
	
	/**
	 * 获得WindowData对象
	 * @param messageId - 消息ID
	 * @return WindowData
	 */
	public static WindowData getWindowData(int messageId) {
		return WAIT_WINDOWS.get(messageId);
	}
	
	/**
	 * 清空所有
	 */
	public static void clearAllEvent() {
		WAIT_WINDOWS.clear();
	}
	
	/**
	 * 获得等待消息的集合大小
	 */
	public static int getWindowDataSize() {
		return WAIT_WINDOWS.size();
	}
	
	private MessageWaitProcessor() {
		
	}

}
