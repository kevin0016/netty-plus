package com.itkevin.nettyplus.clientcommunication.resolve;

public class ShortResolve implements IResolve<Short> {

	@Override
	public Class<Short> getResolveClass() {
		return Short.class;
	}

	@Override
	public Short resolve(String res) {
		return Short.valueOf(res);
	}

}
