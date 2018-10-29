package com.itkevin.nettyplus.clientcommunication.core.hotkey;

/**
 * 异步回调事件对象
 * @author chengang
 *
 */
public class CallBackEvent {
	
	/**
	 * 是否成功回调
	 */
	private boolean success;
	
	/**
	 * 返回值，如果success为false，则为异常信息；否则则是接口返回值，注意判断一下。
	 */
	private transient Object returnObject;
	
	public CallBackEvent(Exception e) {
		this.success = false;
		this.returnObject = e;
	}
	
	public CallBackEvent(Object returnObject) {
		if(returnObject != null && returnObject instanceof Exception) {
			this.success = false;
		} else {
			this.success = true;
		}
		this.returnObject = returnObject;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getReturnObject() {
		return returnObject;
	}

	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

}
