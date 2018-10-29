package com.itkevin.nettyplus.communicationmessage.protocol.serialize;

/**
 * 数据序列化/反序列化接口
 * @author chengang
 *
 */
public interface ISerialize {
	
	/**
	 * 序列化
	 * @param obj - Object
	 * @return byte[]
	 * @throws Exception
	 */
	public byte[] serialize(Object obj) throws Exception;
	
	/**
	 * 反序列化
	 * @param bytes - byte[]
	 * @param clazz - 转换的Class类型
	 * @return T
	 * @throws Exception
	 */
	public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception;

}
