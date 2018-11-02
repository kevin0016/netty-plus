package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import java.util.Map;

/**
 * @ClassName: IProxyFactory
 * @Description: 代理对象工厂
 * @Author: Kevin
 * @CreateDate: 18/11/2 上午10:10
 * @UpdateUser:
 * @UpdateDate: 18/11/2 上午10:10
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface IProxyFactory {

    /**
     * 初始化命令
     *
     * @param commandMap - Map<String, CommandInfo>
     */
    public void init(Map<String, CommandInfo> commandMap);

    /**
     * 获得一个代理
     *
     * @param mapping - 获得一个指定的代理
     * @return IProxyStub
     */
    public IProxyStub getProxy(String mapping);

}
