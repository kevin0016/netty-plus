package com.itkevin.nettyplus.nettycommunication.utils;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NameableThreadFactory implements ThreadFactory {
	
	private final ThreadGroup group;
	private final String namePrefix;
	private final AtomicInteger threadId;
	private final boolean isDaemon;

	public NameableThreadFactory(String namePrefix, boolean isDaemon) {
		SecurityManager s = System.getSecurityManager();
		this.group = (s != null ? s.getThreadGroup() : Thread.currentThread().getThreadGroup());
		this.namePrefix = namePrefix;
		this.threadId = new AtomicInteger(0);
		this.isDaemon = isDaemon;
	}

	@Override
	public Thread newThread(Runnable r) {
		Thread t = new Thread(this.group, r, this.namePrefix + "-" + this.threadId.getAndIncrement());
		t.setDaemon(this.isDaemon);
		if (t.getPriority() != 5) {
			t.setPriority(3);
		}
		return t;
	}
}
