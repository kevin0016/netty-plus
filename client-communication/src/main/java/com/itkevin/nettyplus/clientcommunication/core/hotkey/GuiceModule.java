package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Scopes;
import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.clientcommunication.core.utils.files.ClassScannerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class GuiceModule extends AbstractModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GuiceModule.class);

	@Override
	protected void configure() {
		scannerClass();
	}

	/**
	 * 将指定包下的类扫描并且加入guice容器
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void scannerClass() {
		String scannerPackage = Global.getInstance().getClientConfig().getBasePackages();
		LOGGER.info("scannerPackage -> " + scannerPackage);

		Collection<Class<?>> types = ClassScannerFactory.getInstance().classScanner(scannerPackage).getTypesAnnotatedWith(ProxyModule.class);
		
		if(types != null && !types.isEmpty()) {
			for (Class type : types) {
				//此功能是远程接口
				if(type.isAnnotationPresent(Controller.class)) {
					if(type.isInterface()) {
						Object proxy = CommunicationInvocationHandler.newInstance(type);
						
						this.bind(type).toInstance(proxy);
						
						//顺便将接口方法映射关系缓存起来
						MethodHolder.analyzeClass(type);
					}
				} else {//本地的代理接口
					ProxyModule m = (ProxyModule)type.getAnnotation(ProxyModule.class);
					Class<?> from = m.from();
					if(from != void.class) {//如果没有指定from，则获取此类的所有的接口，如果接口==1则直接使用此接口[没有实现此功能]
						this.bind((Key<Class<?>>) Key.get(m.from()))
							.to((Key<Class<?>>) Key.get(type))
							.in(m.singleton()?Scopes.SINGLETON:Scopes.NO_SCOPE);
					} else {
						this.bind(type).in(m.singleton()?Scopes.SINGLETON:Scopes.NO_SCOPE);
					}
				}
			}
		}
	}

}
