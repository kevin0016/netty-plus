package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
/**
 * 代理实现类
 * @author chengang
 *
 */
public interface IProxyStub {
	
	/**
	 * 调用具体的代理方法
	 * @param context - BeatContext
	 * @throws ServiceFrameException
	 */
	public void invoke(BeatContext context) throws ServiceFrameException;

}
