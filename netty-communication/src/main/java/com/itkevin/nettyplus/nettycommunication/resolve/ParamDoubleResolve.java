package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamDoubleResolve implements IResolve<Double> {

	@Override
	public Class<Double> getResolveClass() {
		return Double.class;
	}

	@Override
	public Double resolve(BeatContext context, String paramName) {
		return Double.valueOf(Double.parseDouble(context.getUrlParams().get(paramName)));
	}

}
