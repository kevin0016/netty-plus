package com.itkevin.nettyplus.nettycommunication.core.context;

import com.itkevin.nettyplus.communicationmessage.protocol.Protocol;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageFromType;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.message.RequestMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import com.itkevin.nettyplus.nettycommunication.core.hotkey.CommandInfo;
import com.itkevin.nettyplus.nettycommunication.core.session.DefaultSession;
import com.itkevin.nettyplus.nettycommunication.core.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.Map;

/**
 * @ClassName: BeatContext
 * @Description: Kevin写点注释吧。。。
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:26
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:26
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public class BeatContext {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeatContext.class);

    private static final ThreadLocal<BeatContext> beatKey = new ThreadLocal<>();

    /**
     * 设置当前线程对象
     *
     * @param context
     */
    public static void setContext(BeatContext context) {
        beatKey.set(context);
    }

    /**
     * 获取当前对象
     *
     * @return
     */
    public static BeatContext getContext() {
        BeatContext context = beatKey.get();
        if (null == context) {
            throw new IllegalStateException("BeatContext");
        }
        return context;
    }

    /**
     * 清除对象
     */
    public static void clearContext() {
        beatKey.remove();
    }

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 服务类型
     */
    private ServerType serverType;

    /**
     * 远程IP
     */
    private String remoteIP;

    /**
     * 远程端口
     */
    private int remotePort;

    /**
     * 本地IP
     */
    private String localIP;

    /**
     * 本地端口
     */
    private int localPort;

    /**
     * session
     */
    private Session session;

    /**
     * 协议对象
     */
    private Protocol protocol;

    /**
     * 消息ID
     */
    private int messageId;

    /**
     * 请求地址
     */
    private String mapping;

    /**
     * 请求
     */
    private RequestMessage request;

    /**
     * 响应
     */
    private ResponseMessage response;

    /**
     * url中的参数
     */
    public Map<String, String> urlParams;

    /**
     * 命令信息
     */
    private CommandInfo command;

    /**
     * 方法执行开始时间
     */
    private long invokeBeginTime;

    /**
     * 方法执行结束时间
     */
    private long invokeEndTime;

    /**
     * 是否执行前置过滤器
     */
    private boolean requestFilter = true;

    /**
     * 是否执行后置过滤器
     */
    private boolean responseFilter = true;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getMapping() {
        return mapping;
    }

    public void setMapping(String mapping) {
        this.mapping = mapping;
    }

    public RequestMessage getRequest() {
        return request;
    }

    public void setRequest(RequestMessage request) {
        this.request = request;
    }

    public ResponseMessage getResponse() {
        return response;
    }

    public void setResponse(ResponseMessage response) {
        this.response = response;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * 返回URL参数，Map不为空
     *
     * @return Map<String       ,               String>
     */
    public Map<String, String> getUrlParams() {
        if (urlParams == null) {
            return Collections.emptyMap();
        }
        return urlParams;
    }

    public void setUrlParams(Map<String, String> urlParams) {
        this.urlParams = urlParams;
    }

    public CommandInfo getCommand() {
        return command;
    }

    public void setCommand(CommandInfo command) {
        this.command = command;
    }

    public ServerType getServerType() {
        return serverType;
    }

    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    public String getRemoteIP() {
        return remoteIP;
    }

    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public String getLocalIP() {
        return localIP;
    }

    public void setLocalIP(String localIP) {
        this.localIP = localIP;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public long getInvokeBeginTime() {
        return invokeBeginTime;
    }

    public void setInvokeBeginTime(long invokeBeginTime) {
        this.invokeBeginTime = invokeBeginTime;
    }

    public long getInvokeEndTime() {
        return invokeEndTime;
    }

    public void setInvokeEndTime(long invokeEndTime) {
        this.invokeEndTime = invokeEndTime;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public boolean isRequestFilter() {
        return requestFilter;
    }

    public void setRequestFilter(boolean requestFilter) {
        this.requestFilter = requestFilter;
    }

    public boolean isResponseFilter() {
        return responseFilter;
    }

    public void setResponseFilter(boolean responseFilter) {
        this.responseFilter = responseFilter;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    /**
     * 组装没有deviceId的连接的请求参数，仅用于连接,不能用于实际的业务
     *
     * @param ctx        - ChannelHandlerContext
     * @param serverType - 服务类型
     * @return BeatContext
     */
    public static BeatContext wrapNoSessionContext(ChannelHandlerContext ctx, ServerType serverType) {
        BeatContext context = new BeatContext();
        context.serverType = serverType;

        context.session = new DefaultSession(ctx, null);
        ;

        //获取IP和端口信息
        Channel channel = ctx.channel();

        InetSocketAddress remoteAddress = (InetSocketAddress) channel.remoteAddress();
        context.remoteIP = remoteAddress.getAddress().getHostAddress();
        context.remotePort = remoteAddress.getPort();

        InetSocketAddress localAddress = (InetSocketAddress) channel.localAddress();
        context.localIP = localAddress.getAddress().getHostAddress();
        context.localPort = localAddress.getPort();

        return context;
    }

    /**
     * 组装请求参数
     *
     * @param msg        - MessageRequest
     * @param session    - Session
     * @param serverType - 服务类型
     * @return BeatContext
     */
    public static BeatContext wrapContext(Protocol protocol, Session session, ServerType serverType) {
        BeatContext context = new BeatContext();
        context.deviceId = session.getDeviceId();
        context.session = session;
        context.serverType = serverType;
        context.protocol = protocol;
        context.messageId = protocol.getMessageId();

        if (protocol.getMessageType() == MessageType.Request) {
            context.request = (RequestMessage) protocol.getEntity();
            context.mapping = context.request.getMapping();
        }

        //获取IP和端口信息
        Channel channel = session.getCtx().channel();

        InetSocketAddress remoteAddress = (InetSocketAddress) channel.remoteAddress();
        context.remoteIP = remoteAddress.getAddress().getHostAddress();
        context.remotePort = remoteAddress.getPort();

        InetSocketAddress localAddress = (InetSocketAddress) channel.localAddress();
        context.localIP = localAddress.getAddress().getHostAddress();
        context.localPort = localAddress.getPort();

        //设置返回对象
        context.setResponse(new ResponseMessage(context.getDeviceId()));

        return context;
    }

    public void writeRequest() throws Exception {

    }

    public void writeResponse() throws Exception {
        //记录消息发送时间
        this.getResponse().setMessageTime(System.currentTimeMillis());

        LOGGER.info("Server Response Message : " + FastJsonHelper.toJson(this.getResponse()));

        this.protocol.setFromType(MessageFromType.SERVER);
        this.protocol.setEntity(this.getResponse());
        writeMessage(this.protocol);
    }

    /**
     * 发送消息
     *
     * @param protocol - 消息协议对象
     */
    public void writeMessage(Protocol protocol) {
        if (protocol != null) {
            this.session.getCtx().writeAndFlush(protocol);
        }
    }

}
