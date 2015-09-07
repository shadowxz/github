package com.sheeps.test;

import java.sql.Timestamp;

/**
 * git
 * 
 * @author admin
 * @Date 2015-8-21 17:30:56
 */
public class Java {
	
	private String a = "a";
	
	public static void main(String[] args) {
		
		System.out.println("corp branch merge master" );
		System.out.println();
		System.out.println(new Timestamp(System.nanoTime()/1000000));
	}
	
	
	class A{
		public A(){
			System.out.println(a);
		}
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
