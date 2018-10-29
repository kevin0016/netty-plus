package com.itkevin.nettyplus.clientcommunication.resolve;

public class StringResolve implements IResolve<String> {

	@Override
	public Class<String> getResolveClass() {
		return String.class;
	}

	@Override
	public String resolve(String res) {
		return res;
	}

}
