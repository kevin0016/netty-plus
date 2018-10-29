package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 所有的远程方法代理集合
 * @author chengang
 *
 */
public final class MethodHolder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodHolder.class);
	
	/**
	 * 所有的代理方法映射集合
	 */
	private static final Map<Class<?> , Map<String , MethodInfo>> ALL_PROXY_METHOD_MAP = new HashMap<>();
	
	
	private MethodHolder() {
		
	}
	
	/**
	 * 解析要远程代理的接口类，并且将方法等信息缓存起来
	 * @param proxyClass - 代理接口类
	 */
	public static synchronized void analyzeClass(Class<?> proxyClass) {
		LOGGER.info("analyzeClass methods by proxyClass : " + proxyClass.getName());
		
		Method[] methods = proxyClass.getMethods();
		if(methods == null || methods.length == 0) {
			LOGGER.warn("proxyClass : " + proxyClass.getName() + " has not method!");
			return;
		}
		
		Map<String , MethodInfo> methodsMap = new HashMap<>();
		for(Method m : methods) {
			RequestMapping mapping = m.getAnnotation(RequestMapping.class);
			if(mapping != null) {
				LOGGER.info("proxyClass : " + proxyClass.getName() + ",method : " + m.getName() + ",mapping : " + mapping.value());
				methodsMap.put(m.getName(), new MethodInfo(m , mapping.value() , mapping.method() == RequestMethod.SYNC));
			}
		}
		
		ALL_PROXY_METHOD_MAP.put(proxyClass, methodsMap);
	}
	
	/**
	 * 根据要调用的类和方法名或者方法描述信息
	 * @param proxyClass - 远程接口类
	 * @param methodName - 方法名称
	 * @return MethodInfo
	 */
	public static MethodInfo getMethodInfo(Class<?> proxyClass , String methodName) {
		Map<String , MethodInfo> methods = ALL_PROXY_METHOD_MAP.get(proxyClass);
		if(methods == null || methods.isEmpty()) {
			return null;
		}
		
		return methods.get(methodName);
	}

}
