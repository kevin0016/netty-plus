package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.itkevin.nettyplus.communicationmessage.protocol.exception.RemoteException;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;

import java.util.concurrent.TimeUnit;

/**
 * Future Interface
 * @author chengang
 *
 */
public interface Future {
	
	/**
	 * 取消任务
	 * @return boolean
	 */
	boolean cancel();
	
	/**
	 * 判断任务是否被取消
	 * @return boolean
	 */
	boolean isCancelled();
	
	/**
     * 阻塞获得结果
     *
     * @return result.
     */
    String get() throws InterruptedException, RemoteException;
    
    /**
     * 阻塞获得结果
     *
     * @param clazz - 要转换的类Class
     * @return result.
     */
    <T>T get(Class<T> clazz) throws InterruptedException, RemoteException;

    /**
     * 阻塞获得结果并设置超时时间，最大阻塞时间不能超过 {@link com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig#asyncRequestTimeout}
     *
     * @param timeout - 超时时间
     * @param unit - 超时单位
     * @return result.
     */
    String get(int timeout, TimeUnit unit) throws InterruptedException, RemoteException;

    /**
     * 阻塞获得结果并设置超时时间，最大阻塞时间不能超过 {@link com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig#asyncRequestTimeout}
     *
     * @param timeout - 超时时间
     * @param unit - 超时单位
     * @param clazz - 要转换的类Class
     * @return result.
     */
    <T> T get(int timeout, TimeUnit unit, Class<T> clazz) throws InterruptedException, RemoteException;
    
    /**
     * 接收返回数据
     * @param message - IMessage
     */
    public void received(IMessage message);
    
    /**
     * 检查任务是否已完成
     *
     * @return done or not.
     */
    boolean isDone();

}
