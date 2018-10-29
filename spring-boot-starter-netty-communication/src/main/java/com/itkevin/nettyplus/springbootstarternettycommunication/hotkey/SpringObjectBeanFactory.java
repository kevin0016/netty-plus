package com.itkevin.nettyplus.springbootstarternettycommunication.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.hotkey.IObjectBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringObjectBeanFactory implements IObjectBeanFactory {
	
	private ConfigurableApplicationContext context;
	
	private BeanDefinitionRegistry beanDefinitonRegistry;
	
	public SpringObjectBeanFactory(ApplicationContext context) {
		this.context = (ConfigurableApplicationContext) context;
		this.beanDefinitonRegistry = (BeanDefinitionRegistry)this.context.getBeanFactory();
	}

	@Override
	public Object createBean(Class<?> clazz) throws Exception {
		String beanName = clazz.getName();
		
		if(!context.containsBean(beanName)) {
			BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
			// get the BeanDefinition
			BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
			// register the bean
			beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
		}
		return context.getBean(beanName);
	}

}
