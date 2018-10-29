package com.itkevin.nettyplus.nettycommunication.resolve;


import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamIntegerResolve implements IResolve<Integer> {

	@Override
	public Class<Integer> getResolveClass() {
		return Integer.class;
	}

	@Override
	public Integer resolve(BeatContext context, String paramName) {
		return Integer.valueOf(Integer.parseInt(context.getUrlParams().get(paramName)));
	}

}
