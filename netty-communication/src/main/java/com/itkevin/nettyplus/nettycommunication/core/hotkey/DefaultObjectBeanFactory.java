package com.itkevin.nettyplus.nettycommunication.core.hotkey;

/**
 * @ClassName: DefaultObjectBeanFactory
 * @Description: 默认对象工厂
 * @Author: Kevin
 * @CreateDate: 18/11/2 上午11:54
 * @UpdateUser:
 * @UpdateDate: 18/11/2 上午11:54
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class DefaultObjectBeanFactory implements IObjectBeanFactory {

    @Override
    public Object createBean(Class<?> clazz) throws Exception {
        return clazz.newInstance();
    }

}
