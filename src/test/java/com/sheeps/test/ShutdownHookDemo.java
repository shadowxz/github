package com.sheeps.test;

/**
 * 
 * 
 * @author admin
 * @version 1.0
 * @time 2015年9月14日 下午6:06:40
 */
public class ShutdownHookDemo {
    
    public ShutdownHookDemo() {
        init();
    }
    
    public void init(){
        System.out.println(this.getClass().getName()+" init ...");
        Runtime.getRuntime().addShutdownHook(new MyHook());
    }
    
    public static void main(String[] args) {
        ShutdownHookDemo demo = new ShutdownHookDemo();
        try {
            Thread.sleep(2000);
            System.out.println(demo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private class MyHook extends Thread{
        public void run() {
            System.out.println("this is MyHook ... ");
        }
    }
    
}
