package com.itkevin.nettyplus.clientcommunication.core.session;

import com.itkevin.nettyplus.clientcommunication.core.hotkey.IAsyncCallBackListener;
import com.itkevin.nettyplus.clientcommunication.core.hotkey.IReturnHandlerListener;
import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.DeviceStatus;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.TimeoutException;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

/**
 * Session接口
 * @author chengang
 *
 */
public interface ISession {
	
	/**
	 * 发送请求并接收返回数据，此方法为阻塞方法。如果代理方法的返回值为void，将返回body为空的ResponseMessage
	 * @param p - 请求协议对象
	 * @return IMessage
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws Exception
	 */
	public IMessage request(Protocol p) throws IOException, TimeoutException, Exception;
	
	/**
	 * 发送请求，此方法为异步方法。如果代理方法的返回值为void，将返回body为空的ResponseMessage
	 * @param p - 请求协议对象
	 * @param returnHandler - 返回消息处理
	 * @param callBacks - 异步回调对象数组
	 * @param future - 是否是Future模式
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws Exception
	 */
	public void requestAsync(Protocol p, IReturnHandlerListener returnHandler, IAsyncCallBackListener[] callBacks, boolean future) throws IOException, TimeoutException, Exception;
	
	/**
	 * 接收请求并发送返回
	 * @param p - 接收协议对象
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws Exception
	 */
	public void receive(Protocol p) throws IOException, TimeoutException, Exception;
	
	/**
	 * 获得当前设备状态
	 * @return DeviceStatus
	 */
	public DeviceStatus getDeviceStatus();
	
	/**
	 * 设置当前设备状态
	 * @param status - DeviceStatus
	 */
	public void setDeviceStatus(DeviceStatus status);
	
	/**
	 * 修改当前设备的状态，并且发送一条状态变更的消息
	 * @param status - DeviceStatus
	 * @throws InterruptedException
	 * @throws SessionException
	 */
	public void setDeviceStatusNotifyServer(DeviceStatus status) throws InterruptedException, SessionException;
	
	/**
	 * 获得ChannelHandlerContext对象，可以直接通过此对象操作Channel进行消息的发送
	 * @return ChannelHandlerContext
	 */
	public ChannelHandlerContext getCtx();
	
	/**
	 * 关闭Session，同时将ChannelHandlerContext关闭
	 */
	public void close();
	
	/**
	 * 判断Session是否被关闭
	 * @return boolean
	 */
	public boolean isClosed();

}
