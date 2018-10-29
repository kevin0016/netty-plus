package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamStringResolve implements IResolve<String> {

	@Override
	public Class<String> getResolveClass() {
		return String.class;
	}

	@Override
	public String resolve(BeatContext context , String paramName) {
		return context.getUrlParams().get(paramName);
	}

}
