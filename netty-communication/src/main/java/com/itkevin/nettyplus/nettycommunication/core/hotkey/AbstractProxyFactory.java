package com.itkevin.nettyplus.nettycommunication.core.hotkey;

import com.itkevin.nettyplus.nettycommunication.core.context.BeatContext;
import com.itkevin.nettyplus.nettycommunication.utils.AntPathMatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractProxyFactory implements IProxyFactory {
	
	private List<CommandInfo> allListCommand;
	private AntPathMatcher pathMatcher = new AntPathMatcher();
	
	private Map<String , IProxyStub> proxyMap = new HashMap<>();
	private Map<String , CommandInfo> commandMap = new HashMap<>();
	
	/**
	 * 初始化
	 * @param commandMap - Map<String, CommandInfo>
	 */
	public void init(Map<String, CommandInfo> commandMap) {
		this.commandMap = commandMap;
		this.allListCommand = new ArrayList<>();
		
		//需要映射每个command对应的proxy
		Map<Class<?> , Map<String , CommandInfo>> commandClassMap = new HashMap<>();
		if(commandMap != null && !commandMap.isEmpty()) {
			for(Map.Entry<String, CommandInfo> me : commandMap.entrySet()) {
				CommandInfo info = me.getValue();
				Class<?> clazz = info.getClazz();
				
				Map<String , CommandInfo> methods = commandClassMap.get(clazz);
				if(methods == null) {
					methods = new HashMap<>();
					commandClassMap.put(clazz, methods);
				}
				
				methods.put(me.getKey(), info);
				this.allListCommand.add(info);
			}
			
			//组织完数据后，开始创建代理类
			for(Map.Entry<Class<?>, Map<String , CommandInfo>> me : commandClassMap.entrySet()) {
				Class<?> clazz = me.getKey();
				Map<String , CommandInfo> methods = me.getValue();
				
				try {
					//创建command实例
					DefaultProxyStub proxy = new DefaultProxyStub(getBeanFactory().createBean(clazz));
					//创建mappding和proxy的映射关系
					methods.forEach((k,v) -> proxyMap.put(k, proxy));//mapping与method映射
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	@Override
	public IProxyStub getProxy(String mapping) {
		IProxyStub proxy = proxyMap.get(mapping);
		if(proxy != null) {
			BeatContext.getContext().setCommand(commandMap.get(mapping));
			return proxy;
		}
		
		if(proxy == null) {
			//TODO 此处耦合了，不好
			//优化，可以将AntPathMatcher匹配后的结果缓存起来。。。。
			for(CommandInfo info : this.allListCommand) {
				if(pathMatcher.match(info.getMapping(), mapping)) {
					Map<String , String> paramMap = pathMatcher.extractUriTemplateVariables(info.getMapping(), mapping);
					//绑定变量
					BeatContext.getContext().setUrlParams(paramMap);
					BeatContext.getContext().setCommand(info);
					
					return proxyMap.get(info.getMapping());
				}
			}
		}
		
		return null;
	}
	
	public abstract IObjectBeanFactory getBeanFactory();

}
