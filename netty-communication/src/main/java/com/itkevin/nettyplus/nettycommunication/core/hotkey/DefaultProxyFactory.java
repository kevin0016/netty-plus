package com.itkevin.nettyplus.nettycommunication.core.hotkey;

/**
 * 默认的代理工厂类
 * @author chengang
 *
 */
public class DefaultProxyFactory extends AbstractProxyFactory {
	
	private IObjectBeanFactory beanFactory = new DefaultObjectBeanFactory();

	@Override
	public IObjectBeanFactory getBeanFactory() {
		return this.beanFactory;
	}

}
