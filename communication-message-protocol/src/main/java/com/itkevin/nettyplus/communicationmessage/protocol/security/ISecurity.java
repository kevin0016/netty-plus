package com.itkevin.nettyplus.communicationmessage.protocol.security;

/**
 * 数据加密/解密接口
 * @author chengang
 *
 */
public interface ISecurity {
	
	/**
	 * 解密
	 * @param bytes - byte[]
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] decode(byte[] bytes) throws Exception;
	
	/**
	 * 加密
	 * @param bytes - byte[]
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] encode(byte[] bytes) throws Exception;

}
