package com.itkevin.nettyplus.springbootnettytest.server.service;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;

import java.util.List;

public interface ITestService {
	
	public List<String> test(RequestMessage request, int abc);

}
