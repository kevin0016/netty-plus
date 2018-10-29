package com.itkevin.nettyplus.nettycommunication.core.filter;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestLogFilter implements IFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestLogFilter.class);

	@Override
	public void filter(BeatContext context) throws Exception {
		if(!context.isRequestFilter()) {
			return;
		}
	
		RequestMessage request = context.getRequest();
		
		StringBuilder buf = new StringBuilder();
		buf.append("request ---- ").append(",");
		buf.append("remoteIP:").append(context.getRemoteIP()).append(",");
		buf.append("localIP:").append(context.getLocalIP()).append(",");
		buf.append("Server.mapping:" + context.getMapping()).append(",");
		buf.append("Server.DeviceId:" + context.getDeviceId()).append(",");
		
		if(request != null) {
			buf.append("Server.Body:").append(request.getBody()).append(",");
		}
		
		LOGGER.info(buf.toString());
	}
	
	@Override
	public FilterType getFilterType() {
		return FilterType.Request;
	}

}
