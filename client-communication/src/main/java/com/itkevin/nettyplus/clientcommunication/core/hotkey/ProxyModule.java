package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProxyModule {
	
	String value() default "";
	
    Class<?> from() default void.class;
    
    boolean singleton() default true;
}
