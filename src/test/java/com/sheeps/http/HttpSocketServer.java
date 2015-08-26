package com.sheeps.http;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpSocketServer {
	
	public static void main(String[] args) {
		try {
			server();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static void server() throws Exception{
		ServerSocket server = new ServerSocket(9999,0,InetAddress.getByName("127.0.0.1"));
		int n = 0;
		while(true){
			System.out.println("n:"+n);
			Socket socket = server.accept();
			new Thread(new HttpService(socket)).start();
			n++;
		}
	}
	
}
