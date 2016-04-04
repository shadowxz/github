package com.sheep.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.sheep.common.log.LoggerFactory;

/**
 * 新类库中的构件, CyclicBarrier, 用于线程顺序执行
 * 
 * @author t-xiejianqiao
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年6月26日 上午10:58:00
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CyclicBarrierDemo {

    private static final Logger logger      = LoggerFactory.getLogger();
    private static final int    FINISH_LINE = 75;
    private List<Horse>         horses      = new ArrayList<Horse>();
    private ExecutorService     es          = Executors.newCachedThreadPool();
    private CyclicBarrier       barrier;

    public CyclicBarrierDemo(int nHorses, final int pause){

        barrier = new CyclicBarrier(nHorses, new Runnable() {

            public void run() {

                StringBuilder sb = new StringBuilder();

                for (int i = 0; i < FINISH_LINE; i++) {
                    sb.append("=");
                }

                System.out.println(sb.toString());

                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }

                for (Horse horse : horses) {
                    if (horse.getStrides() > FINISH_LINE) {
                        System.out.println(horse + "won!");
                        es.shutdownNow();
                        return;
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (Exception e) {
                    System.out.println("barrier-action sleep interrupted ...");
                }

            }
        });

        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            es.execute(horse);
        }

    }

    public static void main(String[] args) {
        int nHorses = 5;
        int pause = 20;
        new CyclicBarrierDemo(nHorses, pause);
    }

}

class Horse implements Runnable {

    private static int           counter = 0;
    private final int            id      = counter++;
    private int                  strides = 0;
    private static Random        rand    = new Random();
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier){
        Horse.barrier = barrier;
    }

    public synchronized int getStrides() {
        return strides;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        int a = getStrides();
        StringBuilder sb = new StringBuilder("" + a);
        for (int i = 0; i < a; i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }

}
