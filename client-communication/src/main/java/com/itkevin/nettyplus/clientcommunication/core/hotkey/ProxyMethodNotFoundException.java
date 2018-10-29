package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import java.lang.reflect.Method;

public class ProxyMethodNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -632494869687017902L;
	
	public ProxyMethodNotFoundException(Class<?> proxyClass , Method method) {
		super("proxyClass:" +proxyClass.getName()+",method:"+method.getName()+" not found by scan!");
	}
	
	public ProxyMethodNotFoundException(String message) {
        super(message);
    }

}
