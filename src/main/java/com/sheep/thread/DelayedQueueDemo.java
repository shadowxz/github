package com.sheep.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 新类库中的构件,
 * DelayedQueue,
 * 无界队列,按照预定时间顺序执行
 * 
 * @author t-xiejianqiao
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年6月26日 上午11:55:33
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class DelayedQueueDemo {
    
    public static void main(String[] args) {
        
        Random rand = new Random(47);
        
        ExecutorService es = Executors.newCachedThreadPool();
        
        DelayQueue<DelayedTask> queue = new DelayQueue<DelayedTask>();
        
        for (int i = 0; i < 20; i++) {
            queue.put(new DelayedTask(rand.nextInt(1000)));
        }
        
        queue.add(new DelayedTask.EndStentinel(1000, es));
        
        es.execute(new DelayedTaskConsumer(queue));
        
    }
}


class DelayedTask implements Runnable,Delayed{
    
    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence = new ArrayList<DelayedTask>();
    
    public DelayedTask(int delayInMilliseconds){
        this.delta = delayInMilliseconds;
        trigger = System.nanoTime() + TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
        sequence.add(this);
    }
    
    public void run() {
        System.out.println(this + " ");
    }
    
    public String toString() {
        return String.format("[%1$-4d]", delta) + " Task " + id;
    }
    
    public int compareTo(Delayed o) {
        DelayedTask task = (DelayedTask) o;
        if(trigger < task.trigger) return -1;
        if(trigger > task.trigger) return 1;
        return 0;
    }
    
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger- System.nanoTime(), TimeUnit.NANOSECONDS);
    }
    
    public String summary(){
        return String.format("(%1$-4d : %2$-4d)", id,delta);
    }
    
    public static class EndStentinel extends DelayedTask{
        
        private ExecutorService es;
        
        public EndStentinel(int delayInMilliseconds,ExecutorService es) {
            super(delayInMilliseconds);
            this.es = es;
        }
        
        public void run(){
            for (DelayedTask t : sequence) {
                System.out.println(t.summary() + " ");
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow ");
            es.shutdownNow();
        }
        
    }
}


class DelayedTaskConsumer implements Runnable{
    
    private DelayQueue<DelayedTask> q;
    
    public DelayedTaskConsumer(DelayQueue<DelayedTask> q){
        this.q = q;
    }
    
    public void run() {
        try {
            while(!Thread.interrupted()){
                // run task with the current thread
                
                q.take().run();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finish DelayedTaskConsumer");
    }
    
}