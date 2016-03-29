package com.sheep.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 新类库中的构件,
 * PriorityBlockingQueue,
 * 按优先级顺序执行,队列中没有PrioritizedTaskConsumer将堵塞
 * 
 * @author t-xiejianqiao
 * @version [版本号，默认V1.0.0]
 * @Credited 2015年6月26日 下午2:42:37
 * @see       [相关类/方法]
 * @since     [产品/模块版本]
 */
public class PriorityBlockingQueueDemo {
    
    public static void main(String[] args) {
        
        ExecutorService es = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();
        es.execute(new PrioritizedTaskProducer(queue, es));
        es.execute(new PrioritizedTaskConsumer(queue));
        
    }
    
}

class PrioritizedTask implements Runnable,Comparable<PrioritizedTask>{

    private Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();
    
    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }
    
    public int compareTo(PrioritizedTask o) {
        return priority <o.priority?1:(priority>o.priority?-1:0);
    }

    public void run() {
        // TODO Auto-generated method stub
        try {
            // TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println(this);
    }
    
    public String toString() {
        return String.format("[%1$-3d]", priority);
    }
    
    public String summary(){
        return String.format("(%1$-4d : %2$-4d)", id, priority);
    }
    

    public static class EndStentinel extends PrioritizedTask{
        
        private ExecutorService es;
        
        public EndStentinel(ExecutorService es) {
            super(-1);
            this.es = es;
        }
        
        public void run(){
            int count = 0;
            for (PrioritizedTask t : sequence) {
                System.out.println(t.summary() + " --");
                if(++count%5 == 0){
                    System.out.println();
                }
            }
            System.out.println();
            System.out.println(this + " Calling shutdownNow ");
            es.shutdownNow();
        }
        
    }
}

class PrioritizedTaskProducer implements Runnable{

    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService es;
    
    public PrioritizedTaskProducer(Queue<Runnable> queue , ExecutorService es) {
        this.queue = queue;
        this.es = es;
    }
    
    public void run() {
        // Fill it up fast with random priorities
        for (int i = 0; i < 20; i++) {
            //queue.add(new PrioritizedTask(rand.nextInt(10)));
            //Thread.yield();
        }
        
        try {
            for (int i = 0; i < 10; i++) {
                //TimeUnit.MILLISECONDS.sleep(250);
                //queue.add(new PrioritizedTask(rand.nextInt(10)));
            }
            // and jobs, lowest priority first
            for (int i = 1; i < 10; i++) {
                queue.add(new PrioritizedTask(rand.nextInt(i)));
            }
            queue.add(new PrioritizedTask.EndStentinel(es));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable{

    private PriorityBlockingQueue<Runnable> q;
    
    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }
    
    public void run() {
        while(!Thread.interrupted()){
            try {
                System.out.println("PrioritizedTaskConsumer ===");
                q.take().run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Finish PrioritizedTaskConsumer");
    }
    
}
