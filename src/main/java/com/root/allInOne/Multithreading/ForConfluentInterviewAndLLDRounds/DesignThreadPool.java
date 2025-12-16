package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

////FUUUUCCKKKK MEEE I'M ON FIREE,,,PURA THREAD POOL KHUD SE DESIGN AND IMPLEMENT KIYA MAINEEE.....
class ThreaddPool{
    int numThreads;
    final BlockingQueue<Runnable> blockingQueue; //this is a threadsafe queue
    final Queue<Runnable> queue = new LinkedList<>();
    Queue<Thread> threads = new LinkedList<>();

    public ThreaddPool(int numThreads, BlockingQueue<Runnable> taskQueue) {
        this.numThreads = numThreads;
        this.blockingQueue = taskQueue != null ? taskQueue : new LinkedBlockingQueue<>();
    }

    public void executeUsingBlockingQueue() {
        for(int i=0; i<numThreads; i++) {
            Thread t = new Thread(() -> {
                while(true) {
                        if (blockingQueue.size() > 0) {
                            Runnable task = blockingQueue.poll();
                            task.run();
                        }
                }
            });
            //blockingQueueThreads.add(t);
            System.out.println("Created - " + t.getName());
        }
    }
    public void executeWithNormalQueue() {
        for(int i=0; i<numThreads; i++) {
            Thread t = new Thread(() -> {
                while(true) {
                    Runnable task = null;
                    synchronized (queue) {
                        if (queue.size() > 0) {
                            task = queue.poll();
                        }
                    }
                    if(task!=null) task.run();
                }
            });
            threads.add(t);
            System.out.println("Created - " + t.getName());
        }
        int size = threads.size();
        while(size-->0) {
            Thread t  = threads.poll();
            System.out.println("Starting - " + t.getName());
            t.start();
            threads.add(t);
        }
    }
    public void submitTask(Runnable task) {
        blockingQueue.add(task);
        queue.add(task);
    }
}

class MyTask implements Runnable {

    int taskNum = 0;
    int sleepTime = 0;
    MyTask(int taskNum, int sleepTime) {
        this.taskNum = taskNum;
        this.sleepTime = sleepTime;
    }

    @Override
    public void run() {
        System.out.println("I'm task "+ taskNum +"...starting - " + Thread.currentThread().getName());
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("I'm task "+ taskNum +"...ending - " + Thread.currentThread().getName());
    }
}


public class DesignThreadPool {
    public static void main(String[] args) {
        ThreaddPool threaddPool = new ThreaddPool(3, null);
        threaddPool.submitTask(new MyTask(1, 5000));
        threaddPool.submitTask(new MyTask(2, 9000));
        threaddPool.submitTask(new MyTask(3, 3000));
        threaddPool.submitTask(new MyTask(4, 7000));
        threaddPool.submitTask(new MyTask(5, 10000));
        threaddPool.executeWithNormalQueue();
        //threaddPool.shutDown();
    }
}



//class - add jobs in threadpool
//threadpool will have loop or queue
