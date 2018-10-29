package com.itkevin.nettyplus.communicationmessage.protocol.security;

/**
 * 不做任何加解密处理
 * @author chengang
 *
 */
public class UnSecurity implements ISecurity {

	@Override
	public byte[] decode(byte[] bytes) throws Exception {
		return bytes;
	}

	@Override
	public byte[] encode(byte[] bytes) throws Exception {
		return bytes;
	}

}
