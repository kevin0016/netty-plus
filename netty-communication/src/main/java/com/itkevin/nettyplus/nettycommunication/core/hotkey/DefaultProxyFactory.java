package com.itkevin.nettyplus.nettycommunication.core.hotkey;

/**
 * @ClassName: DefaultProxyFactory
 * @Description: 默认的代理工厂类
 * @Author: Kevin
 * @CreateDate: 18/11/2 上午10:58
 * @UpdateUser:
 * @UpdateDate: 18/11/2 上午10:58
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class DefaultProxyFactory extends AbstractProxyFactory {

    private IObjectBeanFactory beanFactory = new DefaultObjectBeanFactory();

    @Override
    public IObjectBeanFactory getBeanFactory() {
        return this.beanFactory;
    }

}
