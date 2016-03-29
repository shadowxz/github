package com.sheep.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

public class NIOServer {
    
    private static final Logger logger = LoggerFactory.getLogger();
    
    // 通道管理器
    private Selector selector;
    
    /**
     * 获得一个ServerSocket通道，并对钙通道做一些初始化的工作
     * 
     * @param port 绑定的端口号
     * @throws IOException 
     */
    public void initServer(int port) throws IOException {
        
        // 获得一个ServerSocket
        ServerSocketChannel ssc = ServerSocketChannel.open();
        
        // 设置通道非堵塞
        ssc.configureBlocking(false);
        
        // 将该通道对应的ServerSocket绑定到port端口
        ssc.socket().bind(new InetSocketAddress(port));
        
        // 获得一个通道管理器
        this.selector = Selector.open();
        
        /* 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT时间，注册该事件后
         * 当事件到达时，selector.select()会返回，如果该事件没有到达selector.select()会一直堵塞；
         */
        ssc.register(selector, SelectionKey.OP_ACCEPT);
        
    }
    
    /**
     * 轮询处理selector的事件
     * 
     * @throws IOException 
     */
    public void listen() throws IOException{
        logger.trace("服务器启动成功。。。");
        
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
                //客户请求连接事件
                if(key.isAcceptable()){
                    
                    ServerSocketChannel  server = (ServerSocketChannel) key.channel();
                    
                    // 获得和客户端的连接
                    SocketChannel sc = server.accept();
                    
                    // 非堵塞
                    sc.configureBlocking(false);
                    
                    // 给客户端发信息
                    sc.write(ByteBuffer.wrap("54321".getBytes()));
                    
                    // 在和客户端连接成功之后，为了可以接受客户端的信息，需要给通道设置读的权限
                    sc.register(selector, SelectionKey.OP_READ);
                    
                }else if(key.isReadable()){
                    
                    read(key);
                    
                }
            }
        }
        
    }

    /**
     * 处理读取客户端发来的信息事件
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
        
        logger.debug("服务端收到的消息 ：" + msg);
        
        sc.write(ByteBuffer.wrap((msg+"-"+System.currentTimeMillis()).getBytes()));
        
    }
    
    
    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8000);
        server.listen();
    }
}
