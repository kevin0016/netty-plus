package com.itkevin.nettyplus.clientcommunication.resolve;

public class FloatResolve implements IResolve<Float> {

	@Override
	public Class<Float> getResolveClass() {
		return Float.class;
	}

	@Override
	public Float resolve(String res) {
		return Float.valueOf(res);
	}

}
