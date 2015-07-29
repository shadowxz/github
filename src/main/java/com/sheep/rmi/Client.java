package com.sheep.rmi;

import java.rmi.Naming;



public class Client {
	
	public static void main(String[] args) {
		
    	try {
    		
    		RmiService rmiService = (RmiService) Naming.lookup("rmi://127.0.0.1:6600/rmiService");
    		rmiService.hello("client invoker ...");
    		print();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void print(){
		System.out.println("abc");
	}
}
