package com.sheeps.tests;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sheeps.annotation.AsyncTest;

public class Tests extends BaseContext {
	
	@Autowired
	private AsyncTest async;
	
	volatile String abc = null;
	
	@Test
	public void t(){
		// System.out.println("t " + application);
		
		System.out.println("async : "+async);
		
		async.asyncPrint(abc);
		
		async.syncPrint();
		
		System.out.println(" main end 1 ...");
		
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(" main end 1 ..." + abc);
	}
	
}
