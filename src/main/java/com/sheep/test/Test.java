package com.sheep.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

public class Test {
	
	private static final Logger logger = LoggerFactory.getLogger();
	
	static volatile int vol = 0;
	
	public static void ts() throws InterruptedException, ExecutionException{
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		ArrayList<Future<Integer>> list = new ArrayList<Future<Integer>>();
		
		for (int i = 0; i < 10; i++) {
			
			list.add(es.submit(new Callable<Integer>() {
				
				public Integer call() throws Exception {
					TimeUnit.MILLISECONDS.sleep(2000);
					return vol++;
				}
				
			}));
			
			System.out.println(" == " + i);
			
		}
		
		for(Future<Integer> f : list){
			System.out.println("@@"+f.get());
		}
		
	}
	
	public static void main(String[] args) throws Throwable {
		// Socket
		
		logger.debug(Thread.currentThread().getThreadGroup().toString());
		
		logger.debug(""+(276471211853411L-276471118779576L));
		
		
	}
	
}

interface Tt {
	int add(int i);
}

class TImpl implements Tt{
	
	public int add(int i) {
		return ++i;
	}
	
}

class MyInvokcationHandler implements InvocationHandler{

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("jdk before ...");
		Object obj = method.invoke(proxy, args);
		System.out.println("jdk end ...");
		return obj;
	}
	
}