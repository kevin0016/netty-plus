package com.itkevin.nettyplus.nettycommunication.core.filter;

import com.itkevin.nettyplus.nettycommunication.core.config.SocketServerConfig;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.core.context.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ExecuteTimeAlarmFilter
 * @Description: 代码执行时间告警输出过滤器
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:28
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:28
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 * ＊
 */
public class ExecuteTimeAlarmFilter implements IFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteTimeAlarmFilter.class);

    @Override
    public void filter(BeatContext context) throws Exception {
        SocketServerConfig config = Global.getInstance().getServiceConfig();
        if (config.isSlowMethod()) {
            long cost = context.getInvokeEndTime() - context.getInvokeBeginTime();
            if (cost >= config.getSlowMethodMillis()) {//调用超过指定毫秒，打印日志
                String msg = "time:" + cost + "ms, remoteIP:" + context.getRemoteIP() + ", mapping:" + context.getMapping();

                LOGGER.warn(msg);
            }
        }
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.Response;
    }

}
