package com.itkevin.nettyplus.springbootstarternettycommunication.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.hotkey.AbstractProxyFactory;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.IObjectBeanFactory;
import org.springframework.context.ApplicationContext;

public class SpringProxyFactory extends AbstractProxyFactory {
	
	private IObjectBeanFactory beanFactory;
	
	public SpringProxyFactory(ApplicationContext context) {
		this.beanFactory = new SpringObjectBeanFactory(context);
	}

	@Override
	public IObjectBeanFactory getBeanFactory() {
		return beanFactory;
	}

}
