package com.itkevin.nettyplus.communicationmessage.protocol.compress;

/**
 * 数据压缩/解压接口
 * @author chengang
 *
 */
public interface ICompress {
	
	/**
	 * 解压缩
	 * @param bytes - byte[]
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] unzip(byte[] bytes) throws Exception;
	
	/**
	 * 压缩
	 * @param bytes - byte[]
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] zip(byte[] bytes) throws Exception;

}
