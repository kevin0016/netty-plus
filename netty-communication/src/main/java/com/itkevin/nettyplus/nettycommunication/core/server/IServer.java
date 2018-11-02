package com.itkevin.nettyplus.nettycommunication.core.server;

import com.itkevin.nettyplus.nettycommunication.core.context.ServerType;

/**
 * @ClassName: IServer
 * @Description: 服务器接口
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:05
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:05
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface IServer {

    /**
     * 开启服务器
     *
     * @throws Exception
     */
    public abstract void start() throws Exception;

    /**
     * 关闭服务器
     *
     * @throws Exception
     */
    public abstract void stop() throws Exception;

    /**
     * 服务类型
     *
     * @return ServerType
     */
    public abstract ServerType getServerType();

}
