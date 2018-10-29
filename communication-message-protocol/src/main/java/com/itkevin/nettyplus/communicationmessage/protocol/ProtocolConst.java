package com.itkevin.nettyplus.communicationmessage.protocol;

/**
 * 协议头占位
 * @author chengang
 *
 */
public final class ProtocolConst {
	
	/**
	 * 最大包长度
	 */
	public static final int MAX_FRAME_LENGTH = 524288;
	
	/**
	 * 开头信息
	 */
	public static final byte[] P_START_TAG = { 18, 17, 13, 10, 9 };
	
	/**
	 * 结束信息
	 */
	public static final byte[] P_END_TAG = { 9, 10, 13, 17, 18 };
	
	/**
	 * 协议版本号
	 */
	public static final byte VERSION = 1;
	
	/**
	 * 协义头15个byte
	 */
	public static final int HEAD_STACK_LENGTH = 15;

	public static final int VERSION_SIZE = 1;
	public static final int TOTAL_LENGTH_SIZE = 4;
	public static final int MESSAGE_ID_SIZE = 4;
	public static final int MESSAGE_FROM_TYPE_SIZE = 1;
	public static final int MESSAGE_TYPE_SIZE = 1;
	public static final int COMPRESS_TYPE_SIZE = 1;
	public static final int SECURITY_TYPE_SIZE = 1;
	public static final int SERIALIZE_TYPE_SIZE = 1;
	public static final int PLATFORM_SIZE = 1;

}
