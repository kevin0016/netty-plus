package com.itkevin.nettyplus.nettycommunication.resolve;


import com.alibaba.fastjson.JSONObject;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class JSONObjectResolve implements IResolve<JSONObject> {
	
	private static final JSONObject EMPTY_OBJECT = new JSONObject();

	@Override
	public Class<JSONObject> getResolveClass() {
		return JSONObject.class;
	}

	@Override
	public JSONObject resolve(BeatContext context , String paramName) {
		String d = context.getRequest().getBody();
		if(d == null) {
			return EMPTY_OBJECT;
		}
		
		return FastJsonHelper.toJsonObject(d);
	}

}
