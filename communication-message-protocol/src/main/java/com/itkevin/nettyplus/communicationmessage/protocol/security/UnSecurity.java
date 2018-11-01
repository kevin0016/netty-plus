package com.itkevin.nettyplus.communicationmessage.protocol.security;

/**
 * @ClassName: UnSecurity
 * @Description: 不做任何加解密处理
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:20
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:20
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 * ＊
 */
public class UnSecurity implements ISecurity {

    @Override
    public byte[] decode(byte[] bytes) throws Exception {
        return bytes;
    }

    @Override
    public byte[] encode(byte[] bytes) throws Exception {
        return bytes;
    }

}
