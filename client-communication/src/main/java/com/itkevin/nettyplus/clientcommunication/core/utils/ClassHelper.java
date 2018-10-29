package com.itkevin.nettyplus.clientcommunication.core.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Class辅助类
 * @author chengang
 *
 */
public final class ClassHelper {
	
	private static final Set<Class<?>> BASIC_CLASS_SET = new HashSet<>();
	
	static {
		BASIC_CLASS_SET.add(Character.class);
		BASIC_CLASS_SET.add(Boolean.class);
		BASIC_CLASS_SET.add(Byte.class);
		BASIC_CLASS_SET.add(Short.class);
		BASIC_CLASS_SET.add(Integer.class);
		BASIC_CLASS_SET.add(Long.class);
		BASIC_CLASS_SET.add(Float.class);
		BASIC_CLASS_SET.add(Double.class);
	}
	
	/**
	 * 判断是否是8个基础类型，8个基本类型的对象类型
	 * @param clazz - Class
	 * @return boolean
	 */
	public static boolean isBasicType(Class<?> clazz) {
		return isPrimitive(clazz) || BASIC_CLASS_SET.contains(clazz);
	}
	
	/**
	 * 判断是否是8个基本类型以及void
	 * @param clazz - Class
	 * @return boolean
	 */
	public static boolean isPrimitive(Class<?> clazz) {
		return clazz.isPrimitive();
	}
	
	private ClassHelper() {
		
	}

}
