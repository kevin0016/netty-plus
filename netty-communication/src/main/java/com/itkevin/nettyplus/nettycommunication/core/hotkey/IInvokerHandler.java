package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

public interface IInvokerHandler {
	
	/**
	 * 调用方法
	 * @param context - BeatContext
	 * @throws Exception
	 */
	public void invoke(BeatContext context) throws Exception;

}
