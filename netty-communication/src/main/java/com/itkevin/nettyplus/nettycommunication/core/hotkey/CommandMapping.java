package com.itkevin.nettyplus.nettycommunication.core.hotkey;

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
public @interface CommandMapping {

	String value() default "";
}
