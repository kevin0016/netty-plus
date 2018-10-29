package com.itkevin.nettyplus.clientcommunication.core.hotkey;

/**
 * 异步消息执行超时
 * @author chengang
 *
 */
public class AsyncTimeoutException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5764367750778749657L;
	
	public AsyncTimeoutException(String message) {
        super(message);
    }
	
	public AsyncTimeoutException(String message , Throwable t) {
        super(message , t);
    }

}
