package com.itkevin.nettyplus.clientcommunication.core.context;

import com.google.inject.Injector;
import com.itkevin.nettyplus.clientcommunication.core.config.SocketClientConfig;

public final class Global {
	
	private static Global INSTANCE = new Global();
	
	public static Global getInstance() {
		return INSTANCE;
	}
	
	private Global() {
		
	}
	
	private Injector injector;
	
	private SocketClientConfig clientConfig;
	
	public SocketClientConfig getClientConfig() {
		return clientConfig;
	}

	public void setClientConfig(SocketClientConfig clientConfig) {
		if(this.clientConfig == null) {
			this.clientConfig = clientConfig;
		} else {
			throw new IllegalStateException("Cannot set configuration information repeatedly");
		}
	}

	public Injector getInjector() {
		return this.injector;
	}
	
	public <T> T resolve(Class<T> clazz) {
        return this.injector.getInstance(clazz);
    }

	public void setInjector(Injector injector) {
		if(this.injector == null) {
			this.injector = injector;
		} else {
			throw new IllegalStateException("Cannot set configuration information repeatedly");
		}
	}

}
