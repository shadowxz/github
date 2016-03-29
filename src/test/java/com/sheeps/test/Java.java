package com.sheeps.test;

import java.sql.Timestamp;


/**
 * git 
 * 
 * @author xiejianqiao 
 * @version 1.0 
 * @time 2015年9月14日 下午6:16:11 
 */
public class Java {
    
    private String a = "a";
    
    public static void main(String[] args) {
        
        System.out.println("corp branch merge master" );
        System.out.println();
        System.out.println(new Timestamp(System.nanoTime()/1000000));
        
    }
    
    
    class A{
        public A(){
            System.out.println(a);
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
