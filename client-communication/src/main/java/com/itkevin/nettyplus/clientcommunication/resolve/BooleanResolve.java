package com.itkevin.nettyplus.clientcommunication.resolve;

public class BooleanResolve implements IResolve<Boolean> {

	@Override
	public Class<Boolean> getResolveClass() {
		return Boolean.class;
	}

	@Override
	public Boolean resolve(String res) {
		return Boolean.valueOf(res);
	}

}
