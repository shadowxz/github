package com.sheep.thread;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OneTest extends Two {

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(5);
        final ExecutorService es = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            es.submit(new Callable<Object>() {
                public Object call() throws Exception {
                    try {
                        while(true){
                            Thread.sleep(300);
                            System.out.println(Thread.currentThread().getName()+" : " + System.currentTimeMillis());
                        }
                    } catch (Exception e) {
                        throw e;
                    }
                }
            });
        }
        es.submit(
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("shutdownNow");
                es.shutdownNow();
            }
        });
        // cdl.await();
        System.out.println("--"+System.currentTimeMillis());
    }

    public void test(Object... arg) {
        System.out.println("two test");
    }

    public void test() {
        System.out.println("one test " + this);
    }

}

class TimeOut extends Thread {
    long time = 6000;
    Thread t;

    public TimeOut(Thread time) {
        t = time;
    }

    public void run() {
        try {
            t.start();
            System.out.println(getName() + "-----------");
            Thread.sleep(time);
            t.interrupt();
            System.out.println(getName() + "+++++++++++");
            isInterrupted();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Two {

    static int a = 0;
    final int c = a++;

    private void test() {
        System.out.println("two test " + this);
    }

    public static void testOut(Two two) {
        two.test();
    }

}

enum NEC implements Serializable {

    ABC {
        void p() {
            System.out.println("p");
        }
    };

    abstract void p();
}
