package com.root.allInOne.Multithreading.TaskScheduler;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

class ThreadSafeTaskSchedular {

    BlockingQueue<Task> blockingQueue  =new LinkedBlockingQueue<>();
    Queue<Task> queue;
    Thread[] arr;

    int numThreads;
   Semaphore semaphore = new Semaphore(1);
//
    ThreadSafeTaskSchedular(List<Task> list, int numThreads) {
        this.numThreads  = numThreads;
        queue = new LinkedList<>(list);
        arr = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            arr[i] = new Thread(() -> {
                while(true) {
                    Task nextTaskToexecute = null;
                    try {
                        semaphore.acquire();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (queue.size() > 0) { nextTaskToexecute = queue.poll(); }
                    semaphore.release();
//                    synchronized (queue) { //Using synchronized (this) Locks the Entire Object
//                        if (queue.size() > 0) {
//                            nextTaskToexecute = queue.poll();
//                        }
//                    }
                    if(nextTaskToexecute == null ) break;
                    nextTaskToexecute.execute();
                }
            });
        }
    }

    void executeTasks() throws InterruptedException {
        for (int i = 0; i < numThreads; i++) {
            arr[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            arr[i].join();
        }
    }
}
public class TaskSchedular {

    public static void main(String[] args) throws InterruptedException {
        List<Task> list = Arrays.asList(new Task(), new Task());
        ThreadSafeTaskSchedular schedular = new ThreadSafeTaskSchedular(list, 3);
        schedular.executeTasks();
    }
}

class Task {
    public void execute() {
        Random rand = new Random();
        try {
            Thread.sleep(1000 * rand.nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}