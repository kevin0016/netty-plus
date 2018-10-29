package com.itkevin.nettyplus.springbootnettytest.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class Client {

	public static void main(String[] args) throws IOException {
		SocketChannel channel = SocketChannel.open();
		channel.connect(new InetSocketAddress("192.168.20.30", 9527));
	}

}
