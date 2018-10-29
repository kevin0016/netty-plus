package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class BeatContextResolve implements IResolve<BeatContext> {

	@Override
	public Class<BeatContext> getResolveClass() {
		return BeatContext.class;
	}

	@Override
	public BeatContext resolve(BeatContext context , String paramName) {
		return context;
	}

}
