package com.itkevin.nettyplus.communicationmessage.protocol.utility;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.ProtocolConst;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.DeviceStatus;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ExceptionType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.ServiceFrameException;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ExceptionMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.HeartBeatMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.StatusMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 协议辅助工具类
 * @author chengang
 *
 */
public final class ProtocolHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProtocolHelper.class);

	/**
	 * 检查头是否是指定的头
	 * @param buf - byte[]
	 * @return true or false
	 */
	public static boolean checkHeadDelimiter(byte[] buf) {
		if (buf.length == ProtocolConst.P_START_TAG.length) {
			for (int i = 0; i < buf.length; i++) {
				if (buf[i] != ProtocolConst.P_START_TAG[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * 创建心跳消息协议
	 * @param fromType - 消息来源
	 * @param deviceId - 设备ID
	 * @param fstConn - 是否第一次请求
	 * @return Protocol
	 */
	public static Protocol createHeartBeatMessage(MessageFromType fromType , String deviceId , boolean fstConn) {
		HeartBeatMessage m = new HeartBeatMessage(deviceId);
		m.setMessageTime(System.currentTimeMillis());
		m.setFstConn(fstConn ? 1 : 0);
		
		return new Protocol(0 , fromType , m);
	}
	
	/**
	 * 创建异常消息协议
	 * @param fromType - 消息来源
	 * @param deviceId - 设备ID
	 * @param ex - 异常类
	 * @return Protocol
	 */
	public static Protocol createExceptionMessage(int messageId , MessageFromType fromType , String deviceId , Throwable ex) {
		ExceptionMessage m = new ExceptionMessage(ExceptionType.OTHER_EXCEPTION.getCode());
		m.setDeviceId(deviceId);
		m.setMessageTime(System.currentTimeMillis());
		
		StringBuilder errMsg = new StringBuilder();
		
		errMsg.append("error time:");
		errMsg.append(System.nanoTime());
		
		errMsg.append("--exType:");
		errMsg.append(ExceptionType.OTHER_EXCEPTION.name());
		
		if(ex != null) {
			errMsg.append("--Message:");
			errMsg.append(ex.getMessage());
			errMsg.append("\n");
			errMsg.append(getStackTrace(ex));
		}
		
		m.setErrMsg(errMsg.toString());
		m.setFromIP("");
		m.setToIP("");
		
		return new Protocol(messageId , fromType , m);
	}
	
	/**
	 * 创建异常消息协议
	 * @param fromType - 消息来源
	 * @param ex - 异常类
	 * @return Protocol
	 */
	public static Protocol createExceptionMessage(MessageFromType fromType , ServiceFrameException ex) {
		ExceptionMessage m = new ExceptionMessage(ex.getExType() != null ? ex.getExType().getCode() : ExceptionType.OTHER_EXCEPTION.getCode());
		m.setDeviceId(ex.getDeviceId());
		m.setMessageTime(System.currentTimeMillis());

		StringBuilder errMsg = new StringBuilder();
		
		errMsg.append("error time:");
		errMsg.append(System.nanoTime());
		
		errMsg.append("--exType:");
		if(ex.getExType() == null) {
			errMsg.append(ExceptionType.OTHER_EXCEPTION.name());
		} else {
			errMsg.append(ex.getExType().name());
		}
		
		errMsg.append("--fromIP:");
		if(ex.getFromIP()!= null) {
			errMsg.append(ex.getFromIP());
		}
		
		errMsg.append("--toIP:");
		if(ex.getToIP()!= null) {
			errMsg.append(ex.getToIP());
		}
		
		errMsg.append("--Message:");
		if (ex.getErrorMsg() != null) {
			errMsg.append(ex.getErrorMsg());
		}
		errMsg.append("\n");
		errMsg.append("Caused by: ");
		errMsg.append(getStackTrace(ex));

		m.setErrMsg(errMsg.toString());
		m.setFromIP(ex.getFromIP());
		m.setToIP(ex.getToIP());
		
		return new Protocol(ex.getMessageId() , fromType , m);
	}
	
	
	/**
	 * 获得错误堆栈信息
	 * @param e - Throwable
	 * @return String
	 */
	private static String getStackTrace(Throwable e) {
		try (Writer writer = new StringWriter(); PrintWriter printWriter = new PrintWriter(writer)) {
			e.printStackTrace(printWriter);
			return writer.toString();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage() , e);
		}

		return "";
	}
	
	/**
	 * 状态状态变更协议
	 * @param fromType - 消息来源
	 * @param deviceId - 设备ID
	 * @param status - 设备状态
	 * @return Protocol
	 */
	public static Protocol createStatusMessage(MessageFromType fromType , String deviceId , DeviceStatus status) {
		StatusMessage m = new StatusMessage(deviceId , status.getCode());
		m.setMessageTime(System.currentTimeMillis());
		
		return new Protocol(0 , fromType , m);
	}
	
	private ProtocolHelper() {
		
	}

}
