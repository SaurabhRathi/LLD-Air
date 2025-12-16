package com.root.allInOne.Multithreading;

import java.util.stream.IntStream;

public class Locks {

//    Use 2 threads to print this sequence - 010101010101010...
    volatile static int count = 0;
    static int n = 20;
    public static void main(String[] args) {
//        Locks locks  = new Locks();
//        Semaphore
        Thread t1 = new Thread(() ->
            IntStream.range(0,n).forEach(i -> work()), "Thread1"
        );
        Thread t2 = new Thread(() ->
                IntStream.range(0,n).forEach(i -> work()), "Thread2"
        );
        t1.start();
        t2.start();
    }

    static void work() {
        if(Thread.currentThread().getName().endsWith("1") && count%2==0) {
            System.out.print("0 ");
            count++;
        } else if (Thread.currentThread().getName().endsWith("1")) {
            while(count%2!=0 && count<=n) {;}

        }
        if(Thread.currentThread().getName().endsWith("2") && count%2==1) {
            System.out.print("1 ");
            count++;
        } else if (Thread.currentThread().getName().endsWith("2")) {
            while(count%2!=1 && count<=n) {;}

        }
    }
    //print nth salary and name of employee from map using only java streams -> sort karke list me list.get(n)
}
