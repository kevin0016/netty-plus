package com.itkevin.nettyplus.clientcommunication.resolve;

public class ByteResolve implements IResolve<Byte> {

	@Override
	public Class<Byte> getResolveClass() {
		return Byte.class;
	}

	@Override
	public Byte resolve(String res) {
		return Byte.valueOf(res);
	}

}
