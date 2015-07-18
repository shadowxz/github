package com.sheep.test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 新类库中的构件,
 * CountDownLatch,
 * 用于执行批量任务(TaskPortion)完后,
 * 再执行某些任务(WaitingTask),
 * 
 * @author t-xiejianqiao
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年6月26日 上午10:47:54
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class CountDownLatchDemo {
	
	static final int SIZE = 100;
	
	public static void main(String[] args) {
		
		ExecutorService es = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(SIZE);
		for (int i = 0; i < 10; i++) {
			es.execute(new WaitingTask(latch));
		}
		
		for (int i = 0; i < SIZE; i++) {
			es.execute(new TaskPortion(latch));
		}
		System.out.println("Launched all tasks ...");
		es.shutdown();
		
	}
}

class TaskPortion implements Runnable {

	private static int counter = 0;
	
	private final int id = counter++;
	
	private static Random rand = new Random(47);
	
	private final CountDownLatch latch;
	
	public TaskPortion(CountDownLatch  latch){
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			doWork();
			latch.countDown();
		} catch (Exception e) {
			
		}
	}

	public void doWork() throws InterruptedException {
		
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
		System.out.println(this + " completed");
		
	}
	
	public String toString(){
		return String.format("%1$-3d", id);
	}

}


class WaitingTask implements Runnable {

	private static int counter = 0;
	
	private final int id = counter++;
	
	private final CountDownLatch latch;
	
	public WaitingTask(CountDownLatch  latch){
		this.latch = latch;
	}
	
	public void run() {
		try {
			latch.await();
			System.out.println("Latch barrier passed for " + this);
		} catch (Exception e) {
			System.out.println(this + " interrupted ...");
		}
	}
	
	public String toString(){
		return String.format("WaitingTask %1$-3d", id);
	}

}