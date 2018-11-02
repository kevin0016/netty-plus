package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import java.lang.annotation.*;

/**     
  *
  * @ClassName:      CommandMapping
  * @Description:    接口映射注解
  * @Author:         Kevin
  * @CreateDate:     18/11/2 上午11:53
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 上午11:53
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommandMapping {

	String value() default "";
}
