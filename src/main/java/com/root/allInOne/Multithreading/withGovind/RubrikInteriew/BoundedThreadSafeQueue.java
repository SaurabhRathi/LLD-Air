package com.root.allInOne.Multithreading.withGovind.RubrikInteriew;

import java.util.LinkedList;
import java.util.Queue;

//Interview: https://leetcode.com/discuss/post/7947916/rubrik-interview-experience-by-anonymous-5r6c/

public class BoundedThreadSafeQueue {

    Queue<Integer> queue = new LinkedList<>();
    volatile int maxSize;
    BoundedThreadSafeQueue(int maxSize) {
        this.maxSize = maxSize;
    }

    //either you use synchronized method or you use synchronized block...ek vakt pe ek hi thread andar aayega boss
    void put(Integer value) throws InterruptedException {
        //ek vakt pe ke hi thread put ke andar aayega
        //jo bhi thread put karne aayyenge will wait if q.size is maxSize
        synchronized (this) {
            while(queue.size() == maxSize) {
                this.wait(); //queue.wait() if synchronized on queue object
                //always use wait() in a while loop only
            }
            queue.add(value);
            this.notify(); //wait and notify can only be in a synchronized block
        }
    }

    synchronized Integer take() throws InterruptedException {
        //ek vakt pe ke hi thread take ke andar aayega
        //jo bhi thread take karne aayyenge will wait if q.size is empty
        while(queue.isEmpty()) {
            System.out.println("Queue is empty...waiting now...");
            this.wait();
        }
        Integer taken = queue.poll();
        this.notify();
        return taken;
    }

    synchronized int size() throws InterruptedException {
        return queue.size();
    }

    public static void main(String[] args) throws InterruptedException {

        //ReentrantLock >
        //hikari pool -> 50 conenctions pahle se hi banake rakte hai
        //semaphore matlab ek reosurce ke 50 copies
        //50 se jyada aaya to block karega

        BoundedThreadSafeQueue boundedThreadSafeQueue = new BoundedThreadSafeQueue(3);
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10; i++) {
                try {
                    boundedThreadSafeQueue.put(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            int count = 0;
            while(count < 10) {
                try {
                    count = boundedThreadSafeQueue.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}


/*
*
*
* abhi ek thread ne lock liya...dusra thread kab tak karega wait?
* starvation ka chance ho sakta (bahot thread )
* reentrant lock me starvation nahi hota...timrout kar sakte ho
* reentrant lock me n reads and 1 write
* performance better
*
*
* */