package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

/**     
  *
  * @ClassName:      IProxyStub
  * @Description:    代理实现类
  * @Author:         Kevin
  * @CreateDate:     18/11/2 上午10:15
  * @UpdateUser:     
  * @UpdateDate:     18/11/2 上午10:15
  * @UpdateRemark:   更新项目
  * @Version:        1.0
  */
public interface IProxyStub {
	
	/**
	 * 调用具体的代理方法
	 * @param context - BeatContext
	 * @throws ServiceFrameException
	 */
	public void invoke(BeatContext context) throws ServiceFrameException;

}
