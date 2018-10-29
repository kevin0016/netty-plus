package com.itkevin.nettyplus.clientcommunication.command;

import com.itkevin.nettyplus.clientcommunication.core.hotkey.Controller;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.Param;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.ProxyModule;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.RequestMapping;

import java.util.List;

@ProxyModule
@Controller
public interface IServiceRemote {
	
	@RequestMapping("/api/test/123")
	public List<String> sayHello(@Param("aa") String x, @Param("b") int b);
	
	@RequestMapping("/api/getTask")
	public String getTask(String json);

}
