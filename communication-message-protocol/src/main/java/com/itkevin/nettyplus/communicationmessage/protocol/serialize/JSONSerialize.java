package com.itkevin.nettyplus.communicationmessage.protocol.serialize;


import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;

import java.nio.charset.Charset;

/**
 * JSON序列化接口
 * @author chengang
 *
 */
public class JSONSerialize implements ISerialize {
	
	private Charset utf8 = Charset.forName("UTF-8");

	@Override
	public byte[] serialize(Object obj) throws Exception {
		return FastJsonHelper.toJson(obj).getBytes(utf8);
	}

	@Override
	public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception {
		return FastJsonHelper.toObject(new String(bytes , utf8), clazz);
	}
	
}
