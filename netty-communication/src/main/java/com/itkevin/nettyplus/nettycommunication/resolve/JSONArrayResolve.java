package com.itkevin.nettyplus.nettycommunication.resolve;

import com.alibaba.fastjson.JSONArray;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class JSONArrayResolve implements IResolve<JSONArray> {
	
	private static final JSONArray EMPTY_OBJECT = new JSONArray();

	@Override
	public Class<JSONArray> getResolveClass() {
		return JSONArray.class;
	}

	@Override
	public JSONArray resolve(BeatContext context , String paramName) {
		String d = context.getRequest().getBody();
		if(d == null) {
			return EMPTY_OBJECT;
		}
		
		return FastJsonHelper.toJsonArray(d);
	}

}
