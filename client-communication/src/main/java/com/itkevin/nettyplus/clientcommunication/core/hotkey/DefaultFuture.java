package com.itkevin.nettyplus.clientcommunication.core.hotkey;

import com.itkevin.nettyplus.clientcommunication.core.context.Global;
import com.itkevin.nettyplus.clientcommunication.core.message.MessageWaitProcessor;
import com.itkevin.nettyplus.clientcommunication.resolve.ResolveFactory;
import com.itkevin.nettyplus.communicationmessage.protocol.enums.MessageType;
import com.itkevin.nettyplus.communicationmessage.protocol.exception.*;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ExceptionMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.IMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.message.ResponseMessage;
import com.itkevin.nettyplus.communicationmessage.protocol.utility.FastJsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * DefaultFuture
 * @author chengang
 *
 */
@ThreadSafe
public class DefaultFuture implements Future {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFuture.class);
	
	/**
	 * 消息ID
	 */
	private final int messageId;
	
	/**
	 * 超时时间
	 */
	private final int timeout;
	
	private final Lock lock = new ReentrantLock();
    private final Condition done = lock.newCondition();
    
    private volatile IMessage response;
    private volatile boolean cancel;
    
    public DefaultFuture(int messageId , int timeout) {
    	this.messageId = messageId;
    	this.timeout = timeout;
    }

	@Override
	public boolean cancel() {
		if(isDone()) {
			return false;
		}
		
		lock.lock();
		try {
			if (!isDone()) {
				this.cancel = true;
				//定时器解除绑定
				MessageWaitProcessor.unregisterEvent(this.messageId);
				//设置返回值为抛出异常
				this.response = new ExceptionMessage(ExceptionType.CANCEL_INVOKE_EXCEPTION.getCode() , "request future has been canceled.");
				//解锁wait锁
				if (done != null) {
					done.signal();
				}
			}
			return false;
		} finally {
			lock.unlock();
		}
	}

	@Override
	public boolean isCancelled() {
		return this.cancel;
	}

	@Override
	public String get() throws InterruptedException, RemoteException {
		return get(timeout , TimeUnit.SECONDS);
	}
	
	@Override
	public <T>T get(Class<T> clazz) throws InterruptedException, RemoteException {
		return FastJsonHelper.toObject(get(timeout , TimeUnit.SECONDS), clazz);
	}

	@Override
	public String get(int timeout, TimeUnit unit) throws InterruptedException, RemoteException {
		long t = unit.toMillis(timeout);
		if(t <= 0) {
			t = Global.getInstance().getClientConfig().getAsyncRequestTimeout() * 1000;
		}
		
		if (!isDone()) {
			long start = System.currentTimeMillis();
			lock.lock();
			try {
				while (!this.isDone()) {
                    done.await(t, TimeUnit.MILLISECONDS);
                    if (this.isDone() || System.currentTimeMillis() - start > t) {
                        break;
                    }
                }
			} catch (InterruptedException e) {
				//移除定时器
				MessageWaitProcessor.unregisterEvent(this.messageId);
                throw new RuntimeException(e);
            } catch (Exception e) {
				LOGGER.error(e.getMessage() , e);
            } finally {
                lock.unlock();
            }
			
			if (!this.isDone()) {
				//移除定时器
				MessageWaitProcessor.unregisterEvent(this.messageId);
                throw new TimeoutException("messageId " + messageId + " Future Mode wait timeout.");
            }
		}
		return returnFromResponse();
	}
	
	@Override
	public <T> T get(int timeout , TimeUnit unit , Class<T> clazz) throws InterruptedException, RemoteException {
		if(clazz == null) {
			throw new IllegalArgumentException("class can't null");
		}
		
		String res = get(timeout , TimeUnit.SECONDS);
		return ResolveFactory.getInstance().resolve(res, clazz);
	}
	
	/**
	 * 处理回调信息
	 * @return Object
	 * @throws RemoteException
	 */
	private String returnFromResponse() throws RemoteException {
		if (this.response == null) {
            throw new IllegalStateException("response cannot be null");
        }
		
		MessageType type = this.response.messageType();
		switch(type) {
			case Response:
				return ((ResponseMessage)this.response).getBody();
			case Exception:
				ExceptionMessage ex = (ExceptionMessage)this.response;
				throw ThrowErrorHelper.throwServiceError(ex.getErrCode(), ex.getErrMsg());
			default :
				throw new ProtocolException("Unable to process data information!");
		}
	}

	@Override
	public boolean isDone() {
		return this.response != null;
	}
	
	@Override
	public void received(IMessage message) {
		lock.lock();
		try {
			this.response = message;
            if (done != null) {
                done.signal();
            }
		} finally {
            lock.unlock();
        }
	}

}
