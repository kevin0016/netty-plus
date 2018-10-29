package com.itkevin.nettyplus.clientcommunication.core.hotkey;

/**
 * 超出队列容量异常
 * @author chengang
 *
 */
public class ExceedCapacityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5764367750778749657L;
	
	public ExceedCapacityException(String message) {
        super(message);
    }
	
	public ExceedCapacityException(String message , Throwable t) {
        super(message , t);
    }

}
