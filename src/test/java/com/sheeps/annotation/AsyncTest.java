package com.sheeps.annotation;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTest {
	
	@Async
	public String asyncPrint(String abc){
		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("this is async ... ");
		abc="321";
		return abc="123";
	}
	
	public void syncPrint(){
		System.out.println("this is sync ... ");
	}
	
}
