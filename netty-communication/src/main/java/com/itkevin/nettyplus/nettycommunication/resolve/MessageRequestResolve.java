package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class MessageRequestResolve implements IResolve<RequestMessage> {
	
	@Override
	public Class<RequestMessage> getResolveClass() {
		return RequestMessage.class;
	}

	@Override
	public RequestMessage resolve(BeatContext context , String paramName) {
		return context.getRequest();
	}

}
