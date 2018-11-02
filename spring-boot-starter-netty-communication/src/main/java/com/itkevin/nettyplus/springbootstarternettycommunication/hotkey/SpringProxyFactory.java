package com.itkevin.nettyplus.springbootstarternettycommunication.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.hotkey.AbstractProxyFactory;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.IObjectBeanFactory;
import org.springframework.context.ApplicationContext;
/**     
  *
  * @ClassName:      SpringProxyFactory
  * @Description:    spring代理对象工厂
  * @Author:         Kevin
  * @CreateDate:     18/11/2 下午2:04
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 下午2:04
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public class SpringProxyFactory extends AbstractProxyFactory {
	
	private IObjectBeanFactory beanFactory;
	
	public SpringProxyFactory(ApplicationContext context) {
		this.beanFactory = new SpringObjectBeanFactory(context);
	}

	@Override
	public IObjectBeanFactory getBeanFactory() {
		return beanFactory;
	}

}
