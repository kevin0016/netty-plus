package com.itkevin.nettyplus.clientcommunication.core.context;


import com.itkevin.nettyplus.clientcommunication.core.session.ISessionConnectionListener;

import java.util.ArrayList;
import java.util.List;

/**     
  *
  * @ClassName:      CommunicationListener
  * @Description:    通信监听器工具类
  * @Author:         Kevin
  * @CreateDate:     18/11/2 上午11:12
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 上午11:12
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public final class CommunicationListener {
	
	/**
	 * Session连接监听器
	 */
	private static final List<ISessionConnectionListener> SESSION_CONNECTION_LISTENER_LIST = new ArrayList<>();
	
	/**
	 * 添加一个Session连接监听器
	 * @param listener - ISessionConnectionListener
	 */
	public static void addSessionConnectionListener(ISessionConnectionListener listener) {
		SESSION_CONNECTION_LISTENER_LIST.add(listener);
	}
	
	/**
	 * 移除一个Session连接监听器
	 * @param listener - ISessionConnectionListener
	 */
	public static void removeSessionConnectionListener(ISessionConnectionListener listener) {
		SESSION_CONNECTION_LISTENER_LIST.remove(listener);
	}
	
	/**
	 * 清空Session连接监听器
	 */
	public static void clearSessionConnectionListener() {
		SESSION_CONNECTION_LISTENER_LIST.clear();
	}
	
	/**
	 * 获取所有的Session连接监听器
	 * @return List<ISessionConnectionListener>
	 */
	public static List<ISessionConnectionListener> getSessionConnectionListener() {
		return SESSION_CONNECTION_LISTENER_LIST;
	}

}
