package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import java.lang.annotation.*;

/**
 * 标注方法参数名称
 * @author chengang
 *
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Param {
	
	/**
	 * 映射名称
	 * @return String
	 */
	String value();

}
