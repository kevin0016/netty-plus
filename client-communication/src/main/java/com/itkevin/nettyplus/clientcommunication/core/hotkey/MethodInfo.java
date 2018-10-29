package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.itkevin.nettyplus.clientcommunication.core.utils.ClassHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 命令描述
 * @author chengang
 *
 */
public class MethodInfo {
	
	/**
	 * 所属类
	 */
	private final Class<?> clazz;
	
	/**
	 * 所属的方法
	 */
	private final Method actionMethod;
	
	/**
	 * 映射的URL
	 */
	private final String mapping;
	
	/**
	 * 方法的返回类型
	 */
	private final Class<?> returnType;
	
	/**
	 * 参数名称集合
	 */
	private final String[] paramNames;
	
	/**
	 * 参数类型集合
	 */
	private final Class<?>[] paramTypes;
	
	/**
	 * 是否是同步方法
	 */
	private final boolean sync;
	
	/**
	 * 是否是future模式
	 */
	private final boolean future;
	
	public MethodInfo(Method method , String mapping , boolean sync) {
		this.clazz = method.getDeclaringClass();
		this.actionMethod = method;
		this.mapping = mapping;
		this.paramTypes = getMethodParamTypes();
	    this.paramNames = getMethodParamNames();
	    this.returnType = method.getReturnType();
	    //如果返回的对象是Future对象，则sync必是异步
	    if(this.returnType == Future.class) {
	    	this.future = true;
		    this.sync = false;//非同步方法
	    } else {
		    this.sync = sync;
	    	this.future = false;
	    }
	}
	
	/**
	 * 解析方法的参数类型
	 * @return Class<?>[]
	 */
	private Class<?>[] getMethodParamTypes() {
		Method method = this.actionMethod;
		Class<?>[] classes = method.getParameterTypes();
		
		if(classes != null && classes.length > 0) {
			List<Class<?>> classList = new ArrayList<>();
			for(Class<?> clazz : classes) {
				if(clazz != IAsyncCallBackListener.class) {
					classList.add(clazz);
				}
			}
			
			return classList.toArray(new Class<?>[0]);//返回实际的参数类型数组
		}
		
		return new Class<?>[0];
	}
	
	/**
	 * 解析方法的参数名称
	 * @return String[]
	 */
	private String[] getMethodParamNames() {
		Method method = this.actionMethod;
		//安卓不支持method.getParameters()方法
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		Class<?>[] parameterClasses = method.getParameterTypes();
		if(parameterAnnotations != null && parameterAnnotations.length > 0) {
			//如果是一个参数， 需要额外的逻辑
			if(parameterAnnotations.length == 1) {
				//如果是基本类型，则必须要加@Param注解
				if(ClassHelper.isBasicType(parameterClasses[0])) {
					Param param = findParam(parameterAnnotations[0]);
					if(param == null) {
						throw new RuntimeException("proxyClass:"+this.clazz.getName()+",method:"+method.getName()+",parameter have not @Param!");
					}
					
					return new String[] {param.value()};
				} else if(parameterClasses[0] == IAsyncCallBackListener.class) {//有回调对象
					return new String[0];
				} else {
					//否则，不加和加都可以
					Param param = findParam(parameterAnnotations[0]);
					if(param == null) {
						return new String[0];
					} else {
						return new String[] {param.value()};
					}
				}
			} else {
				List<String> paramNamesList = new ArrayList<>();
				for(int i=0;i<parameterAnnotations.length;i++) {
					if(parameterClasses[i] != IAsyncCallBackListener.class) {
						Param param = findParam(parameterAnnotations[i]);
						if(param == null) {
							throw new RuntimeException("proxyClass:"+this.clazz.getName()+",method:"+method.getName()+",parameter have not @Param!");
						}
						
						paramNamesList.add(param.value());
					}
				}
				return paramNamesList.toArray(new String[0]);//返回实际的参数名称数组
			}
		}
		
		return new String[0];
	}
	
	private Param findParam(Annotation[] annotationArray) {
		if(annotationArray != null && annotationArray.length > 0) {
			for(Annotation a : annotationArray) {
				if(a.annotationType() == Param.class) {
					return (Param)a;
				}
			}
		}
		return null;
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public Method getActionMethod() {
		return actionMethod;
	}

	public String getMapping() {
		return mapping;
	}

	public String[] getParamNames() {
		return paramNames;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public boolean isSync() {
		return sync;
	}

	public boolean isFuture() {
		return future;
	}

}
