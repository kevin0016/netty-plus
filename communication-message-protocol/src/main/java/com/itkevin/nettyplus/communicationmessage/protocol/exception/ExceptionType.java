package com.itkevin.nettyplus.communicationmessage.protocol.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 异常返回码
 * @author chengang
 *
 */
public enum ExceptionType {
	
	SUCCESS(0 , "成功" , null),
	DB(1 , "数据库错误" , DBException.class),
	NET(2 , "网络连接异常" , NetException.class),
	TIME_OUT(3 , "接口调用超时出错" , TimeoutException.class),
	PROTOCOL(4 , "协议错误" , ProtocolException.class),
	JSON_SERIALIZE_EXCEPTION(5 , "JSON序列化出错" , JSONSerializeException.class),
	PARA_EXCEPTION(6 , "参数传递出错" , ParaException.class),
	NOT_FOUND_METHOD_EXCEPTION(7 , "没有找到要调用的方法" , NotFoundMethodException.class),
	DATA_OVER_FLOW_EXCEPTION(8 , "数据溢出" , DataOverFlowException.class),
	SERVICE_INVOKE_EXCEPTION(9 , "服务调用异常" , ServiceInvokeException.class),
	REBOOT_EXCEPTION(10 , "服务重启" , RebootException.class),
	CANCEL_INVOKE_EXCEPTION(11 , "取消调用" , CancelInvokeExpcetion.class),
	OTHER_EXCEPTION(99 , "其它错误" , OtherException.class)
	;
	
	/**
	 * 错误编码
	 */
	private final int code;
	
	/**
	 * 错误描述
	 */
	private final String errorMsg;
	
	/**
	 * 异常类的Class
	 */
	private final Class<?> exceptionClass;
	
	private static final Map<Integer , ExceptionType> RETURN_TYPE_MAP = new HashMap<>();
	
	static {
		for(ExceptionType ert : ExceptionType.values()) {
			RETURN_TYPE_MAP.put(ert.code, ert);
		}
	}
	
	ExceptionType(int code , String errorMsg , Class<?> exceptionClass) {
		this.code = code;
		this.errorMsg = errorMsg;
		this.exceptionClass = exceptionClass;
	}

	public int getCode() {
		return code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	public Class<?> getExceptionClass() {
		return exceptionClass;
	}

	public static ExceptionType getByCode(int code) {
		return RETURN_TYPE_MAP.get(code);
	}

}
