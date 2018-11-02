package com.itkevin.nettyplus.nettycommunication.core.hotkey;
/**     
  *
  * @ClassName:      IObjectBeanFactory
  * @Description:    普通对象工厂
  * @Author:         Kevin
  * @CreateDate:     18/11/2 上午10:36
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 上午10:36
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public interface IObjectBeanFactory {
	
	/**
	 * 注册指定的Class
	 * @param clazz - Class
	 * @return Object
	 * @throws Exception
	 */
	public Object createBean(Class<?> clazz) throws Exception;

}
