package com.sheep.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

public class NIOClient {
	
	private static final Logger logger = LoggerFactory.getLogger();
	
	private Selector selector;
	
	public void initClient(String addr,int port) throws IOException{
		
		SocketChannel sc = SocketChannel.open();
		
		sc.configureBlocking(false);
		
		selector = Selector.open();
		
		sc.connect(new InetSocketAddress(addr,port));
		
		sc.register(selector, SelectionKey.OP_CONNECT);
	}
	
	public void listen() throws IOException{
		
		while(true){
			// 当注册的事件到达时候，方法返回，否则，该方法一直堵塞
			selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
			SelectionKey key = null;
			while(iterator.hasNext()){
				key = iterator.next();
				// 删除以防止重复处理；
				iterator.remove();
				//连接事件
				if(key.isConnectable()){
					
					SocketChannel  sc = (SocketChannel) key.channel();
					
					// 如果正在连接，则完成连接
					if(sc.isConnectionPending()){
						sc.finishConnect();
					}
					
					
					// 非堵塞
					sc.configureBlocking(false);
					
					// 给服务端发信息
					sc.write(ByteBuffer.wrap("12345".getBytes()));
					
					// 在和服务端连接成功之后，为了可以接受服务端的信息，需要给通道设置读的权限
					sc.register(selector, SelectionKey.OP_READ);
					
				}else if(key.isReadable()){
					
					read(key);
					
				}
			}
		}
		
	}

	/**
	 * 处理读取服务端发来的信息事件
	 * 
	 * @param key
	 * @throws IOException 
	 */
	public void read(SelectionKey key) throws IOException {
		// 服务端可读消息：得到事件发生的Socket通道
		SocketChannel sc = (SocketChannel) key.channel();
		
		// 创建读取的缓冲区
		ByteBuffer buffer = ByteBuffer.allocate(5);
		
		sc.read(buffer);
		
		String msg = new String(buffer.array());
		
		logger.debug("客户端收到的消息 ："+msg);
		
		sc.write(ByteBuffer.wrap((msg+"-"+System.currentTimeMillis()).getBytes()));
		
	}
	
	
	public static void main(String[] args) throws IOException {
		
		NIOClient client = new NIOClient();
		
		client.initClient("localhost", 8000);
		
		client.listen();
		
	}
}
