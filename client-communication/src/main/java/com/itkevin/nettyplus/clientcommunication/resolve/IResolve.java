package com.itkevin.nettyplus.clientcommunication.resolve;

/**
 * 基础的解析接口
 * @author chengang
 *
 */
public interface IResolve<T> {
	
	/**
	 * 获得需要解析的Class
	 * @return Class<T>
	 */
	public Class<T> getResolveClass();
	
	/**
	 * 解析，返回真正的实体
	 * @param res - String
	 * @return T
	 */
	public T resolve(String res);

}
