package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;
import java.util.Queue;

class ProducerConsumerQueueLock {

    final Queue<Integer> queue = new LinkedList<Integer>();
    final int capacity;

    public ProducerConsumerQueueLock(int capacity) {
        this.capacity = capacity;
    }


    void produce(int limit) throws InterruptedException {

        for(int value=0; value<limit; value++) {
            synchronized(queue) {
                if(queue.size()==capacity) {
                    //can't produce more
                    System.out.println();
                    System.out.println("Producer waiting");
                    queue.wait();
                }
                System.out.println("Producing = " + value);
                queue.add(value);
                queue.notify();
            }
//            Thread.sleep(2000);
        }
        //synchronize on the object
        //wait if capacity is full - can't produce more
        //produce and put into list
        //notify consumer to consume

    }

    void consume(int limit) throws InterruptedException{

        for(int i=0; i<limit; i++) {
            synchronized(queue) {
                if(queue.isEmpty()) {
                    //can't consume more
                    System.out.println();
                    System.out.println("Consumer waiting");
                    queue.wait();
                }
                int value = queue.poll();
                System.out.println("Consuming = " + value);
                queue.notify();
            }
//            Thread.sleep(2000);
        }
        //synchronize on the object
        //wait if can't consume anymore - buffer is empty
        //consume and put into list
        //notify prdocuer to produce
    }

}

public class ProducerConsumerUsingQueueAsLock {
    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 8;
        int limit = 25;
        ProducerConsumerQueueLock producerConsumer = new ProducerConsumerQueueLock(bufferSize);

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.produce(limit);
                } catch (InterruptedException e) {
                    //ignore
                }

            }
        });
        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    producerConsumer.consume(limit);
                } catch (InterruptedException e) {
                    //ignore
                }

            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
