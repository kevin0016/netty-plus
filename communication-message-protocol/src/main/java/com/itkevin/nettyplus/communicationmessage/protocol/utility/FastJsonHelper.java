package com.itkevin.nettyplus.communicationmessage.protocol.utility;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
/**     
  *
  * @ClassName:      FastJsonHelper
  * @Description:    Kevin写点注释吧。。。
  * @Author:         Kevin
  * @CreateDate:     18/11/1 下午6:22
  * @UpdateUser:     
  * @UpdateDate:     18/11/1 下午6:22
  * @UpdateRemark:   更新项目
  * @Version:        1.0
＊*/
public final class FastJsonHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FastJsonHelper.class);
	
	/**
	 * 空的JSON字符串
	 */
	public static final String EMPTY_JSON_STRING = "{}";
	
	/**
	 * 空的immutable JSONObject类
	 */
	public static final JSONObject EMPTY_JSON = new JSONObject(Collections.emptyMap());
	
	/**
	 * 空的immutable JSONArray类
	 */
	public static final JSONArray EMPTY_ARRAY = new JSONArray(Collections.emptyList());
	
	public static <T> T toObject(String json, Class<T> clazz) {
		if (json == null || json.length() == 0)
			return null;

		try {
			return JSON.parseObject(json, clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}

	public static <T> String toJson(T entity) {
		if (entity == null)
			return null;

		try {
			return JSON.toJSONString(entity);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <K, V> Map<K, V> toMap(String json) {
		if (json == null || json.length() == 0)
			return new HashMap<K, V>();

		try {
			return JSON.parseObject(json, HashMap.class);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}

		return new HashMap<K, V>();
	}

	/**
	 * 将json array 字符串 转换为指定的集合类型
	 * @param jsonArrayStr - String
	 * @param clazz - Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> toList(String jsonArrayStr, Class<T> clazz) {
		List<T> result = new ArrayList<T>();

		if (jsonArrayStr != null && jsonArrayStr.length() > 0) {
			try {
				
				return JSON.parseArray(jsonArrayStr, clazz);
			} catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
			}
		}

		return result;
	}
	
	/**
	 * 将JSONArray转换为指定类型的集合
	 * @param array - JSONArray
	 * @param clazz - Class<T>
	 * @return List<T>
	 */
	public static <T> List<T> toList(JSONArray array , Class<T> clazz) {
		if(array == null || array.isEmpty()) return null;
		
		try {
			return array.toJavaList(clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage() , e);
		}
		
		return null;
	}

	/**
	 * 将字符串转换成FastJson对象<br>
	 * 如果字符串为空或者发生异常，将返回一个不可修改的非空JSONObject
	 * @param json - String
	 * @return JSONObject
	 */
	public static JSONObject toJsonObject(String json) {
		if (json == null || json.length() == 0) {
			return EMPTY_JSON;
		}
		try {
			return JSONObject.parseObject(json);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return EMPTY_JSON;
	}
	
	/**
	 * 将字符串转换成FastJson对象<br>
	 * 如果字符串为空或者发生异常，将返回一个不可修改的非空JSONObject
	 * @param json - String
	 * @param ordered - 是否按照文本顺序显示
	 * @return JSONObject
	 */
	public static JSONObject toJsonObject(String json , boolean ordered) {
		if (json == null || json.length() == 0) {
			return EMPTY_JSON;
		}
		try {
			if(ordered) {
				return JSONObject.parseObject(json , Feature.OrderedField);
			} else {
				return JSONObject.parseObject(json);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return EMPTY_JSON;
	}

	/**
	 * 将字符串转换成FastJson对象<br>
	 * 如果字符串为空或者发生异常，将返回一个不可修改的非空JSONArray
	 * @param json - String
	 * @return JSONArray，返回的对象请不要进行任何写入操作
	 */
	public static JSONArray toJsonArray(String json) {
		if (json == null || json.length() == 0) {
			return EMPTY_ARRAY;
		}
		try {
			return JSONObject.parseArray(json);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return EMPTY_ARRAY;
	}
	
	private FastJsonHelper() {
		
	}

}
