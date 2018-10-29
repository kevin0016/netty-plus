package com.itkevin.nettyplus.clientcommunication.resolve;

import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;

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
		addResolve(new JSONArrayResolve());
		addResolve(new JSONObjectResolve());
		
		
		addResolve(new StringResolve());
		
		IResolve<?> c = new BooleanResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Boolean.TYPE , c);
		
		c = new ByteResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Byte.TYPE , c);
		
		c = new CharacterResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Character.TYPE , c);
		
		c = new DoubleResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Double.TYPE , c);
		
		c = new FloatResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Float.TYPE , c);
		
		c = new IntegerResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Integer.TYPE , c);
		
		c = new LongResolve();
		addResolve(c.getResolveClass() , c);
		addResolve(Long.TYPE , c);
		
		c = new ShortResolve();
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
	
	@SuppressWarnings("unchecked")
	public <T> T resolve(String res , Class<T> clazz){
		IResolve<?> resolve = resolveMap.get(clazz);
		if(resolve != null) {
			return (T)resolve.resolve(res);
		}
		
		return objectResolve(res, clazz);
	}
	
	private <T> T objectResolve(String res , Class<T> clazz){
		if(res == null) {
			return null;
		}
		
		return FastJsonHelper.toObject(res, clazz);
	}

}
