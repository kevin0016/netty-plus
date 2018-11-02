package com.itkevin.nettyplus.nettycommunication.core.context;

import com.itkevin.nettyplus.nettycommunication.core.config.SocketServerConfig;
import com.itkevin.nettyplus.nettycommunication.core.filter.FilterType;
import com.itkevin.nettyplus.nettycommunication.core.filter.IFilter;
import com.itkevin.nettyplus.nettycommunication.core.init.IInit;
import com.itkevin.nettyplus.nettycommunication.core.server.IServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Global
 * @Description: 全局配置类
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:27
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:27
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public final class Global {

    private static final Logger LOGGER = LoggerFactory.getLogger(Global.class);

    private static final Global INSTANCE = new Global();

    private SocketServerConfig serviceConfig;

    /**
     * 服务初始化列表
     */
    private final List<IInit> initList = new ArrayList<>();

    /**
     * 服务器列表
     */
    private final List<IServer> serverList = new ArrayList<>();

    /**
     * 请求过滤器
     */
    private final List<IFilter> requestFilterList = new ArrayList<>();

    /**
     * 应答过滤器
     */
    private final List<IFilter> responseFilterList = new ArrayList<>();

    /**
     * 连接过滤器
     */
    private final List<IFilter> connectionFilterList = new ArrayList<>();

    public static Global getInstance() {
        return INSTANCE;
    }

    private Global() {

    }

    /**
     * 添加初始化接口
     *
     * @param init - IInit
     */
    public void addInit(IInit init) {
        synchronized (this.initList) {
            this.initList.add(init);
        }
    }

    /**
     * 添加服务器列表
     *
     * @param server - IServer
     */
    public void addServer(IServer server) {
        synchronized (this.serverList) {
            this.serverList.add(server);
        }
    }

    /**
     * 添加请求过滤器
     *
     * @param filter - IFilter
     */
    public void addRequestFilter(IFilter filter) {
        if (filter.getFilterType() == FilterType.Request) {
            synchronized (this.requestFilterList) {
                this.requestFilterList.add(filter);
            }
        } else {
            LOGGER.warn("filter class(" + filter.getClass().getName() + ") can't add in request filter list!");
        }
    }

    /**
     * 移除请求过滤器
     *
     * @param filter - IFilter
     */
    public void removeRequestFilter(IFilter filter) {
        synchronized (this.requestFilterList) {
            this.requestFilterList.remove(filter);
        }
    }

    /**
     * 添加应答过滤器
     *
     * @param filter - IFilter
     */
    public void addResponseFilter(IFilter filter) {
        if (filter.getFilterType() == FilterType.Response) {
            synchronized (this.responseFilterList) {
                this.responseFilterList.add(filter);
            }
        } else {
            LOGGER.warn("filter class(" + filter.getClass().getName() + ") can't add in response filter list!");
        }
    }

    /**
     * 移除应答过滤器
     *
     * @param filter - IFilter
     */
    public void removeResponseFilter(IFilter filter) {
        synchronized (this.responseFilterList) {
            this.responseFilterList.remove(filter);
        }
    }

    /**
     * 添加连接过滤器
     *
     * @param filter - IFilter
     */
    public void addConnectionFilter(IFilter filter) {
        if (filter.getFilterType() == FilterType.CONNECTION) {
            synchronized (this.connectionFilterList) {
                this.connectionFilterList.add(filter);
            }
        } else {
            LOGGER.warn("filter class(" + filter.getClass().getName() + ") can't add in connection filter list!");
        }
    }

    /**
     * 移除连接过滤器
     *
     * @param filter - IFilter
     */
    public void removeConnectionFilter(IFilter filter) {
        synchronized (this.connectionFilterList) {
            this.connectionFilterList.remove(filter);
        }
    }

    public List<IInit> getInitList() {
        return initList;
    }

    public List<IServer> getServerList() {
        return serverList;
    }

    public List<IFilter> getRequestFilterList() {
        return requestFilterList;
    }

    public List<IFilter> getResponseFilterList() {
        return responseFilterList;
    }

    public List<IFilter> getConnectionFilterList() {
        return connectionFilterList;
    }

    public SocketServerConfig getServiceConfig() {
        return serviceConfig;
    }

    public void setServiceConfig(SocketServerConfig serviceConfig) {
        if (this.serviceConfig == null) {
            this.serviceConfig = serviceConfig;
        } else {
            throw new IllegalStateException("Cannot set configuration information repeatedly");
        }
    }

}
