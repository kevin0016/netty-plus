package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamLongResolve implements IResolve<Long> {

	@Override
	public Class<Long> getResolveClass() {
		return Long.class;
	}

	@Override
	public Long resolve(BeatContext context, String paramName) {
		return Long.valueOf(Long.parseLong(context.getUrlParams().get(paramName)));
	}

}
