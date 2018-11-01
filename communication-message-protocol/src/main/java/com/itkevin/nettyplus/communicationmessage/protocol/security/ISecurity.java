package com.itkevin.nettyplus.communicationmessage.protocol.security;

/**
 * @ClassName: ISecurity
 * @Description: 数据加密/解密接口
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:21
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:21
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface ISecurity {

    /**
     * 解密
     *
     * @param bytes - byte[]
     * @return byte[]
     * @throws Exception
     */
    public byte[] decode(byte[] bytes) throws Exception;

    /**
     * 加密
     *
     * @param bytes - byte[]
     * @return byte[]
     * @throws Exception
     */
    public byte[] encode(byte[] bytes) throws Exception;

}
