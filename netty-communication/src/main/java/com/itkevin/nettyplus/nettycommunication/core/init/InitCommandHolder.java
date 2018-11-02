package com.itkevin.nettyplus.nettycommunication.core.init;

import com.itkevin.nettyplus.nettycommunication.core.config.SocketServerConfig;
import com.itkevin.nettyplus.nettycommunication.core.context.Global;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.CommandHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: InitCommandHolder
 * @Description: 初始化
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:04
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:04
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class InitCommandHolder implements IInit {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitCommandHolder.class);

    @Override
    public void init() {
        SocketServerConfig serviceConfig = Global.getInstance().getServiceConfig();

        LOGGER.info("----------------init command holder--------------------");
        CommandHolder.init(serviceConfig.getBasePackages(), serviceConfig.getProxyFactory());
        LOGGER.info("----------------init command holder finish--------------");
    }

}
