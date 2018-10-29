package com.itkevin.nettyplus.springbootnettytest.server.service.impl;

import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.springbootnettytest.server.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements ITestService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

	@Override
	public List<String> test(RequestMessage request, int abc) {
		/*if(true) {
			throw new NullPointerException("我就是异常");
		}*/
		
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		return list;
	}

}
