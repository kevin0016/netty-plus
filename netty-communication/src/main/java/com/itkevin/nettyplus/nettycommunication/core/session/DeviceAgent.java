package com.itkevin.nettyplus.nettycommunication.core.session;

import com.itkevin.nettyplus.communicationmessage.protocol.enums.DeviceStatus;

/**
 * @ClassName: DeviceAgent
 * @Description: 设备信息对外接口
 * @Author: Kevin
 * @CreateDate: 18/11/2 下午12:06
 * @UpdateUser:
 * @UpdateDate: 18/11/2 下午12:06
 * @UpdateRemark: 更新项目
 * @Version: 1.0
 */
public final class DeviceAgent {

    /**
     * 判断设备是否已连接
     *
     * @param deviceId - 设备ID
     * @return boolean
     */
    public static final boolean isConnection(String deviceId) {
        return SessionManager.getInstance().getSession(deviceId) != null;
    }

    /**
     * 获得当前设备的状态信息
     *
     * @param deviceId - 设备ID
     * @return DeviceStatus
     */
    public static final DeviceStatus getDeviceStatus(String deviceId) {
        Session session = SessionManager.getInstance().getSession(deviceId);
        if (session == null) {
            return DeviceStatus.UNKNOWN;
        }

        return session.getDeviceStatus();
    }

    /**
     * 修改设备的当前状态
     *
     * @param deviceId - 设备ID
     * @param status   - DeviceStatus
     * @throws InterruptedException
     * @throws SessionException
     */
    public static final void changeDeviceStatus(String deviceId, DeviceStatus status) throws InterruptedException, SessionException {
        Session session = SessionManager.getInstance().getSession(deviceId);
        if (session == null) {
            throw new SessionException("current client session is null");
        }
        //修改设备状态
        session.setDeviceStatusNotifyClient(status);
    }

    private DeviceAgent() {

    }

}
