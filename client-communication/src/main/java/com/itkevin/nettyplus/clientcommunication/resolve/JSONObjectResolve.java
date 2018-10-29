package com.itkevin.nettyplus.clientcommunication.resolve;

import com.alibaba.fastjson.JSONObject;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;

public class JSONObjectResolve implements IResolve<JSONObject> {
	
	private static final JSONObject EMPTY_OBJECT = new JSONObject();

	@Override
	public Class<JSONObject> getResolveClass() {
		return JSONObject.class;
	}

	@Override
	public JSONObject resolve(String res) {
		if(res == null) {
			return EMPTY_OBJECT;
		}
		
		return FastJsonHelper.toJsonObject(res);
	}

}
