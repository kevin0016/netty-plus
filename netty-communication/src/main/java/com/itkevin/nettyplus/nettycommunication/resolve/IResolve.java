package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

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
	 * @param context - BeatContext
	 * @param paramName - 参数名称
	 * @return T
	 */
	public T resolve(BeatContext context, String paramName);

}
