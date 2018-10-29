package com.itkevin.nettyplus.communicationmessage.protocol.compress;

/**
 * 不做任何压缩/解压处理
 * @author chengang
 *
 */
public class UnCompress implements ICompress {

	@Override
	public byte[] unzip(byte[] bytes) throws Exception {
		return bytes;
	}

	@Override
	public byte[] zip(byte[] bytes) throws Exception {
		return bytes;
	}

}
