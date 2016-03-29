package com.sheeps.http;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class HttpService implements Runnable {
    
    Socket socket;
    
    public HttpService(Socket socket) {
        this.socket = socket;
    }
    
    
    @Override
    public void run() {
        
        System.out.println("----------------ip : " + socket.getInetAddress() + "---------------");
        try {
            PrintStream out = new PrintStream(socket.getOutputStream());
            InputStreamReader in = new InputStreamReader(socket.getInputStream());
            char[] buffer = new char[1024];
            StringBuilder sb = new StringBuilder();
            in.read(buffer);
            sb.append(buffer);
            System.out.println(sb.toString());
            // 1、首先向浏览器输出响应头信息  
              out.println("HTTP/1.1 200 OK");  
              out.println("Content-Type:text/html;charset=utf-8");  
              out.println();  
              // 2、输出主页信息  
              out .println("<HTML><BODY>"  
                      + "<center>"  
                      + "<H1>HTTP协议测试服务器,当前时间："  
                      + new java.util.Date()  
                      + "</h1>"  
                      + "</center>您提交的数据如下:<pre>test</pre></BODY></HTML>");  
              out.flush();  
              out.close();
              in.close();
              socket.close();
              System.out.println("socket is " + socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
