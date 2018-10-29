package com.itkevin.nettyplus.springbootnettytest.command;

import com.itkevin.nettyplus.clientcommunication.core.hotkey.*;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;

import java.util.List;

@ProxyModule
@Controller
public interface IServiceRemote {
	
	@RequestMapping("/api/test/123")
	public List<String> sayHello(@Param("aa") String x, @Param("b") int b);

	@RequestMapping("/api/test/345")
	public List<String> sayWorld(String json);

	@RequestMapping("/api/test/567")
	public void sayVoid(@Param("aa") String x, @Param("b") int b);

	@RequestMapping("/api/test/789")
	public ResponseMessage sayMessage(@Param("aa") String x, @Param("b") int b);

	@RequestMapping(value="/api/test/911" , method = RequestMethod.ASYNC)
	public List<String> sayAsyncHello(IAsyncCallBackListener callback, @Param("aa") String x, @Param("b") int b, IAsyncCallBackListener callback2);

	@RequestMapping(value="/api/test/888")
	public Future sayFuture(@Param("aa") String x, @Param("b") int b);

}
