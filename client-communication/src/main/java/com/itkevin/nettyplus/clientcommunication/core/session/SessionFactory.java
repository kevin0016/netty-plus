package com.itkevin.nettyplus.clientcommunication.core.session;

import com.itkevin.nettyplus.clientcommunication.core.message.MessageWaitProcessor;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SessionFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactory.class);
	
	private static volatile ISession SESSION;
	
	/**
	 * 获得Session对象
	 * @return ISession
	 */
	public static ISession getSession() {
		return SESSION;
	}

	/**
	 * 创建Session
	 * @param ctx - ChannelHandlerContext
	 * @param deviceId - 设备ID
	 * @return ISession
	 */
	public static ISession buildSession(ChannelHandlerContext ctx , String deviceId) {
		LOGGER.info("build client session!");
		synchronized(SessionFactory.class) {
			if(SESSION != null) {
				SESSION.close();//调用关闭操作
			}
			//开启新的Session
			SESSION = new DefaultSession(ctx, deviceId);
		}
		return SESSION;
	}
	
	/**
	 * 移除当前的session
	 * @return boolean
	 */
	static boolean removeSession() {
		SESSION = null;
		//移除session的时候，顺便清空一下等待消息
		MessageWaitProcessor.clearAllEvent();
		return true;
	}
	
	private SessionFactory() {
		
	}

}
