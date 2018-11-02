package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**     
  *
  * @ClassName:      IResolve
  * @Description:    基础的解析接口
  * @Author:         Kevin
  * @CreateDate:     18/11/2 下午12:41
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 下午12:41
  * @UpdateRemark:   更新项目
  * @Version:        1.0
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
