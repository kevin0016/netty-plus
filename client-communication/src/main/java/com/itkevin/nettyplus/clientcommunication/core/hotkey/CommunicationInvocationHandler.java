package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.clientcommunication.core.message.MessageIdFactory;
import com.itkevin.nettyplus.clientcommunication.core.session.ISession;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionException;
import com.itkevin.nettyplus.clientcommunication.core.session.SessionFactory;
import com.itkevin.nettyplus.clientcommunication.core.utils.ClassHelper;
import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunicationInvocationHandler implements InvocationHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommunicationInvocationHandler.class);

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Class<?> proxyClass = method.getDeclaringClass();
		
		// 如果调用的是从Object继承来的方法，则直接调用。
		if (Object.class.equals(proxyClass)) {
			return method.invoke(this, args);
		}
		//首先判断Session是否正常
		ISession session = SessionFactory.getSession();
		if(session == null || session.isClosed()) {
			throw new SessionException("session not found or session is closed!");
		}
		//获取具体的方法
		MethodInfo methodInfo = MethodHolder.getMethodInfo(proxyClass, method.getName());
		if(methodInfo == null) {
			throw new ProxyMethodNotFoundException(proxyClass , method);
		}
		//将调用的参数过滤掉回调函数
		IAsyncCallBackListener[] callBacks = null;
		if(args != null && args.length > 0) {
			List<Object> argsList = new ArrayList<>(args.length);
			List<IAsyncCallBackListener> callBackList = null;
			
			for(Object o : args) {
				if(o != null && o instanceof IAsyncCallBackListener) {
					if(callBackList == null) {
						callBackList = new ArrayList<>(1);
					}
					callBackList.add((IAsyncCallBackListener)o);
				} else {
					argsList.add(o);
				}
			}
			
			args = argsList.toArray(new Object[0]);
			if(callBackList != null) {
				callBacks = callBackList.toArray(new IAsyncCallBackListener[0]);
			}
		}
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("invoke remote method : " + method);
			LOGGER.debug("handlers size : " + (callBacks != null ? callBacks.length : 0));
			if(args != null && args.length > 0) {
				String[] paramNames = methodInfo.getParamNames();
				if(paramNames.length == 0) {
					LOGGER.debug("invoke remote args " + args[0]);
				} else {
					for(int i=0;i<args.length;i++) {
						LOGGER.debug("invoke remote args " +paramNames[i]+ " : " + args[i]);
					}
				}
			}
		}
		
		if(!methodInfo.isSync()) {//异步调用
			if(methodInfo.isFuture()) {
				Protocol p = createRequestMessage(methodInfo, args);
				//创建Future对象
				Future f = new DefaultFuture(p.getMessageId(), Global.getInstance().getClientConfig().getAsyncRequestTimeout());
				
				SessionFactory.getSession().requestAsync(p, new IReturnHandlerListener() {
					@Override
					public Object handler(IMessage message) throws Exception {
						//解禁future
						f.received(message);
						return message;
					}
				}, callBacks , true);
				return f;//返回Future对象
			} else {
				SessionFactory.getSession().requestAsync(createRequestMessage(methodInfo, args), new IReturnHandlerListener() {

					@Override
					public Object handler(IMessage message) throws Exception {
						return ReturnHandler.handle(methodInfo, message);
					}
					
				}, callBacks , false);
				return null;
			}
		} else {
			long beginTime = System.currentTimeMillis();
			
			//调用远程接口
			try {
				//组装参数协议
				IMessage message = SessionFactory.getSession().request(createRequestMessage(methodInfo, args));
				if(message == null) {
					throw new NullPointerException("invoke remote command return message is null!");
				}
				
				LOGGER.debug("invoke " + methodInfo.getMapping()+ " time:" + (System.currentTimeMillis() - beginTime) + "ms");
				
				//处理返回消息，创建返回值对象
				return ReturnHandler.handle(methodInfo, message);
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	/** 
     * 获取目标对象的代理对象 
     * @param proxyInterface - 要代理的接口
     * @return 代理对象 
     */  
	public static Object newInstance(Class<?> proxyInterface) {
    	CommunicationInvocationHandler handler = new CommunicationInvocationHandler();
    	
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class<?>[]{proxyInterface}, handler);
    }
	
	/**
	 * 创建RequestMessage对象将数据发送到远程服务
	 * @param methodInfo - MethodInfo
	 * @param args - 传递的参数
	 * @return Protocol
	 */
	private Protocol createRequestMessage(MethodInfo methodInfo, Object[] args) {
		RequestMessage request = new RequestMessage(Global.getInstance().getClientConfig().getDeviceId() , methodInfo.getMapping());
		request.setMessageTime(System.currentTimeMillis());
		
		if(args == null || args.length == 0) {
			request.setBody(FastJsonHelper.EMPTY_JSON_STRING);
		} else if(args.length == 1) {
			//判断参数是否是基本类型，如果是基本类型，则转换为JSON格式，如果是复杂类型，则直接走JsonHelper.toJson()方法。
			if(ClassHelper.isBasicType(args[0].getClass())) {
				Map<String , Object> data = new HashMap<>();
				data.put(methodInfo.getParamNames()[0], args[0]);
				
				request.setBody(FastJsonHelper.toJson(data));
			} else {
				//复杂对象需要判断是否有参数名称
				String[] paramNames = methodInfo.getParamNames();
				if(paramNames == null || paramNames.length == 0) {
					if(args[0].getClass() == String.class) {//如果是String，并且没有@Param注解，则直接当JSON发送出去
						request.setBody((String)args[0]);
					} else {
						request.setBody(FastJsonHelper.toJson(args[0]));
					}
				} else {
					Map<String , Object> data = new HashMap<>();
					data.put(paramNames[0], args[0]);
					
					request.setBody(FastJsonHelper.toJson(data));
				}
			}
		} else {
			//映射传输的参数
			Map<String , Object> data = new HashMap<>();
			String[] paramNames = methodInfo.getParamNames();
			for(int i=0;i<args.length;i++) {
				data.put(paramNames[i], args[i]);
			}
			
			request.setBody(FastJsonHelper.toJson(data));
		}
		
		return new Protocol(MessageIdFactory.createMessageId() , MessageFromType.CLIENT , request);
	}

}
