package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public class ParamByteResolve implements IResolve<Byte> {

	@Override
	public Class<Byte> getResolveClass() {
		return Byte.class;
	}

	@Override
	public Byte resolve(BeatContext context, String paramName) {
		return Byte.valueOf(Byte.parseByte(context.getUrlParams().get(paramName)));
	}

}
