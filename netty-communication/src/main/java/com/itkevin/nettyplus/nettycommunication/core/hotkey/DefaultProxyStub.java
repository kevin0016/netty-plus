package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.communicationmessage.protocol.exception.ExceptionType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.resolve.ResolveFactory;
import com.itkevin.nettyplus.nettycommunication.utils.ExceptionFactory;

/**
 * 默认的代理类
 * @author chengang
 *
 */
public class DefaultProxyStub implements IProxyStub {
	
	private static final Object[] EMPTY_ARGS = new Object[]{};
	
	private Object target;
	
	public DefaultProxyStub(Object target) {
		this.target = target;
	}

	@Override
	public void invoke(BeatContext context) throws ServiceFrameException {
		CommandInfo info = context.getCommand();
		if(info == null) {
			throw ExceptionFactory.createServiceFrameException("context mapping " + context.getMapping() + " has not match Method", context , ExceptionType.NOT_FOUND_METHOD_EXCEPTION, null);
		}
		
		try {
			Object returnObject = info.getActionMethod().invoke(target, resolveParameters(context, info));
			if(info.getReturnType() != void.class) {
				//此处需要返回信息
				context.getResponse().setBody(FastJsonHelper.toJson(returnObject));
			}
		} catch (Exception e) {
			throw ExceptionFactory.createServiceFrameException("invoke method : " + context.getMapping() + " error!", context , ExceptionType.SERVICE_INVOKE_EXCEPTION, e);
		}
	}
	
	/**
	 * 解析参数并且按照参数类型返回参数数组
	 * @param context - BeatContext
	 * @param info - CommandInfo
	 * @return Object[]
	 */
	private Object[] resolveParameters(BeatContext context , CommandInfo info) {
		Class<?>[] classes = info.getParamTypes();
		if(classes == null || classes.length == 0) {
			return EMPTY_ARGS;
		}
		String[] paramNames = info.getParamNames();
		
		ResolveFactory resolveFactory = ResolveFactory.getInstance();
		Object[] args = new Object[classes.length];
		for(int i=0;i<classes.length;i++) {
			args[i] = resolveFactory.resolve(context, paramNames[i] , classes[i]);
		}
		
		return args;
	}

}
