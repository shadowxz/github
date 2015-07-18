package com.sheep.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		Car car = new Car();
		ExecutorService ex = Executors.newCachedThreadPool();
		ex.submit(new WaxOff(car));
		ex.submit(new WaxOn(car));
		TimeUnit.MILLISECONDS.sleep(2000);
		ex.shutdownNow();
		
	}
	
}

class WaxOff implements Runnable {
	
	private Car car;

	public WaxOff(Car car) {
		this.car = car;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				car.waitForWaxing();
				System.out.println("wax Off! ");
				car.buffed();
			}
		} catch (Exception e) {
			System.out.println("WaxOff Exiting via interrupt ...");
		}
		System.out.println("Ending Wax Off task ..");
	}
}

class WaxOn implements Runnable {
	
	private Car car;

	public WaxOn(Car car) {
		this.car = car;
	}

	public void run() {
		try {
			while (!Thread.interrupted()) {
				System.out.println("wax On! ");
				car.waxed();
				car.waitForBuffing();
			}
		} catch (Exception e) {
			System.out.println("WaxOn Exiting via interrupt ...");
		}
		System.out.println("Ending Wax On task ..");
	}
}

class Car {
	
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void waxed() {
		lock.lock();
		try {
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void buffed() {
		lock.lock();
		try {
			condition.signalAll();
		} finally {
			lock.unlock();
		}
	}

	public void waitForWaxing() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}

	public void waitForBuffing() throws InterruptedException {
		lock.lock();
		try {
			condition.await();
		} finally {
			lock.unlock();
		}
	}
}