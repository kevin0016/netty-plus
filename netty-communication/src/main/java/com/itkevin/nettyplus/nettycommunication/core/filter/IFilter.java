package com.itkevin.nettyplus.nettycommunication.core.filter;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**
 * 请求过滤器
 * @author chengang
 *
 */
public interface IFilter {
	
	/**
	 * 过滤方法
	 * @param context - BeatContext
	 * @throws Exception
	 */
	public void filter(BeatContext context) throws Exception;
	
	/**
	 * 获得过滤器类型
	 * @return FilterType
	 */
	public FilterType getFilterType();
	
}
