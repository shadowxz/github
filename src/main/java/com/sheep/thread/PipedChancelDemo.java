package com.sheep.thread;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PipedChancelDemo {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// BlockingQueue
		Sender sender = new Sender();
		Receiver receiver = new Receiver(sender);
		
		ExecutorService ex = Executors.newCachedThreadPool();
		
		ex.submit(sender);
		ex.submit(receiver);
		
		TimeUnit.MILLISECONDS.sleep(2000);
		
		ex.shutdownNow();
	}
	
}

class Sender implements Runnable {

	private PipedWriter writer = new PipedWriter();

	public PipedWriter getOut() {
		return writer;
	}

	public void run() {
		try {
			while (true) {
				for (char c = 'A'; c < 'z'; c++) {
					writer.write(c);
					TimeUnit.MILLISECONDS.sleep(100);
				}
			}
		} catch (Exception e) {
			System.out.println(e + " Sender write exception ...");
		}
	}

}

class Receiver implements Runnable {

	private PipedReader reader = new PipedReader();

	public Receiver(Sender sender) throws IOException {
		reader = new PipedReader(sender.getOut());
	}

	public void run() {
		try {
			while (true) {
				System.out.println("read : " + (char) reader.read());
			}
		} catch (Exception e) {
			System.out.println(e + " Receiver write exception ...");
		}
	}

}