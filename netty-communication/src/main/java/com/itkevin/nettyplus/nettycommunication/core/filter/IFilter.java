package com.itkevin.nettyplus.nettycommunication.core.filter;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**
 * @ClassName: IFilter
 * @Description: 请求过滤器
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:30
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:30
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface IFilter {

    /**
     * 过滤方法
     *
     * @param context - BeatContext
     * @throws Exception
     */
    public void filter(BeatContext context) throws Exception;

    /**
     * 获得过滤器类型
     *
     * @return FilterType
     */
    public FilterType getFilterType();

}
