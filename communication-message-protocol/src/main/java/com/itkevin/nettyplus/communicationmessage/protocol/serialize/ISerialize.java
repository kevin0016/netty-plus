package com.itkevin.nettyplus.communicationmessage.protocol.serialize;

/**
 * @ClassName: ISerialize
 * @Description: 数据序列化/反序列化接口
 * @Author: Kevin
 * @CreateDate: 18/11/1 下午6:21
 * @UpdateUser:
 * @UpdateDate: 18/11/1 下午6:21
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public interface ISerialize {

    /**
     * 序列化
     *
     * @param obj - Object
     * @return byte[]
     * @throws Exception
     */
    public byte[] serialize(Object obj) throws Exception;

    /**
     * 反序列化
     *
     * @param bytes - byte[]
     * @param clazz - 转换的Class类型
     * @return T
     * @throws Exception
     */
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception;

}
