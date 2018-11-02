package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**     
  *
  * @ClassName:      IInvokerHandler
  * @Description:    你TM的写不写注释
  * @Author:         Kevin
  * @CreateDate:     18/11/2 上午11:55
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 上午11:55
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public interface IInvokerHandler {
	
	/**
	 * 调用方法
	 * @param context - BeatContext
	 * @throws Exception
	 */
	public void invoke(BeatContext context) throws Exception;

}
