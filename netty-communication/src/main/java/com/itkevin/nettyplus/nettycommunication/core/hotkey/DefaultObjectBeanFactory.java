package com.itkevin.nettyplus.nettycommunication.core.hotkey;

public class DefaultObjectBeanFactory implements IObjectBeanFactory {

	@Override
	public Object createBean(Class<?> clazz) throws Exception {
		return clazz.newInstance();
	}

}
