package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamFloatResolve implements IResolve<Float> {

	@Override
	public Class<Float> getResolveClass() {
		return Float.class;
	}

	@Override
	public Float resolve(BeatContext context, String paramName) {
		return Float.valueOf(Float.parseFloat(context.getUrlParams().get(paramName)));
	}

}
