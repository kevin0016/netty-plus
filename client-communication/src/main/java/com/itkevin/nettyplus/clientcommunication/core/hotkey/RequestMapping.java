package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import java.lang.annotation.*;

/**
 * 接口映射注解
 * 
 * @author chengang
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {

	/**
	 * 通信接口的URL地址
	 * @return String
	 */
	String value() default "";
	
	/**
	 * 通信接口的请求方式
	 * @return RequestMethod
	 */
	RequestMethod method() default RequestMethod.SYNC;
}
