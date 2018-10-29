package com.itkevin.nettyplus.clientcommunication.core.session;

/**
 * Session连接监听器
 * @author chengang
 *
 */
public interface ISessionConnectionListener {
	
	/**
	 * Session连接后调用的事件通知
	 * @param event - SessionConnectionEvent
	 * @return boolean - 如果不执行下一个监听器，则return false
	 */
	public boolean onConnection(SessionConnectionEvent event);

}
