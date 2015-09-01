package com.sheeps.test;

import java.util.Stack;


public class ClssInstance {
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		
		ClassPrivateTest cpt =  ClassPrivateTest.class.newInstance();
		
		System.out.println(cpt);
		
	}
	
}
