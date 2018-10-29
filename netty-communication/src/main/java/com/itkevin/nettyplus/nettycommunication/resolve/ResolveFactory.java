package com.itkevin.nettyplus.nettycommunication.resolve;

import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析工厂类
 * @author chengang
 *
 */
public class ResolveFactory {
	
	private static final ResolveFactory FACTORY = new ResolveFactory();
	
	private final Map<Class<?> , IResolve<?>> resolveMap = new HashMap<>();
	
	private ResolveFactory(){
		addResolve(new BeatContextResolve());
		addResolve(new JSONArrayResolve());
		addResolve(new JSONObjectResolve());
		addResolve(new MessageRequestResolve());
		addResolve(new MessageResponseResolve());
		
		
		addResolve(new ParamStringResolve());
		
		IResolve<?> c = new ParamBooleanResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Boolean.TYPE , c);
		
		c = new ParamByteResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Byte.TYPE , c);
		
		c = new ParamCharacterResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Character.TYPE , c);
		
		c = new ParamDoubleResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Double.TYPE , c);
		
		c = new ParamFloatResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Float.TYPE , c);
		
		c = new ParamIntegerResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Integer.TYPE , c);
		
		c = new ParamLongResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Long.TYPE , c);
		
		c = new ParamShortResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Short.TYPE , c);
	}
	
	public static ResolveFactory getInstance(){
		return FACTORY;
	}
	
	private void addResolve(Class<?> clazz , IResolve<?> resolve) {
		resolveMap.put(clazz , resolve);
	}
	
	private void addResolve(IResolve<?> resolve) {
		addResolve(resolve.getResolveClass() , resolve);
	}
	
	public boolean hasResolve(Class<?> clazz) {
		return resolveMap.containsKey(clazz);
	}
	
	public Object resolve(BeatContext context , String paramName , Class<?> clazz){
		IResolve<?> resolve = resolveMap.get(clazz);
		if(resolve != null) {
			return resolve.resolve(context , paramName);
		}
		
		return objectResolve(context, clazz);
	}
	
	private Object objectResolve(BeatContext context , Class<?> clazz){
		String d = context.getRequest().getBody();
		if(d == null) {
			return null;
		}
		
		return FastJsonHelper.toObject(d, clazz);
	}

}
