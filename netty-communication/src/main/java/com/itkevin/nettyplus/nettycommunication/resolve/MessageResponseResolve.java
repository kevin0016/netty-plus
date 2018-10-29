package com.itkevin.nettyplus.nettycommunication.resolve;


import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class MessageResponseResolve implements IResolve<ResponseMessage> {
	
	@Override
	public Class<ResponseMessage> getResolveClass() {
		return ResponseMessage.class;
	}

	@Override
	public ResponseMessage resolve(BeatContext context , String paramName) {
		return context.getResponse();
	}

}
