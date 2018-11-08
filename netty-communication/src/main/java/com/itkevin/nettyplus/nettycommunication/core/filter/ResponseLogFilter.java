package com.itkevin.nettyplus.nettycommunication.core.filter;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: ResponseLogFilter
 * @Description: 响应日志过滤
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:32
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:32
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class ResponseLogFilter implements IFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseLogFilter.class);

    @Override
    public void filter(BeatContext context) throws Exception {
        if (!context.isResponseFilter()) {
            return;
        }

        RequestMessage request = context.getRequest();
        ResponseMessage response = context.getResponse();

        StringBuilder buf = new StringBuilder();
        buf.append("response ---- ").append(",");
        buf.append("remoteIP:").append(context.getRemoteIP()).append(",");
        buf.append("localIP:").append(context.getLocalIP()).append(",");
        buf.append("Server.mapping:" + context.getMapping()).append(",");
        buf.append("Server.DeviceId:" + context.getDeviceId()).append(",");

        if (request != null) {
            buf.append("Server.Body:").append(request.getBody()).append(",");
        }
        if (response != null) {
            buf.append("ggr").append(response.getBody()).append(",");
        }

        LOGGER.info(buf.toString());
    }

    @Override
    public FilterType getFilterType() {
        return FilterType.Response;
    }

}
