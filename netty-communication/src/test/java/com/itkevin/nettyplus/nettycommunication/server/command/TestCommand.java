package com.itkevin.nettyplus.nettycommunication.server.command;

import com.alibaba.fastjson.JSONObject;
import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.Command;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.CommandMapping;
import com.itkevin.nettyplus.nettycommunication.server.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Command
public class TestCommand {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestCommand.class);
	
	@CommandMapping("/api/test/{abc}")
	public List<String> test(RequestMessage request , int abc , JSONObject json , TestVO vo){
		LOGGER.info("我进来了此方法！！！");
		LOGGER.info("server receive body : " + request.getBody());
		LOGGER.info("abc = " + abc);
		LOGGER.info("json = " + json.toJSONString());
		LOGGER.info("vo = " + FastJsonHelper.toJson(json));
		
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		
		return list;
	}
	
}
