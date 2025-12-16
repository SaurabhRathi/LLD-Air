package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;

class ProducerConsumerBufferLock {

    final LinkedList<Integer> buffer = new LinkedList<Integer>();
    final int capacity;

    public ProducerConsumerBufferLock(int capacity) {
        this.capacity = capacity;
    }


    void produce(int limit) throws InterruptedException {

        for(int value=0; value<limit; value++) {
            synchronized(buffer) {
                if(buffer.size()==capacity) {
                    //can't produce more
                    System.out.println();
                    System.out.println("Producer waiting");
                    buffer.wait();
                }
                System.out.println("Producing = " + value);
                buffer.add(value);
                buffer.notify();
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
            synchronized(buffer) {
                if(buffer.size()==0) {
                    //can't consume more
                    System.out.println();
                    System.out.println("Consumer waiting");
                    buffer.wait();
                }
                int value = buffer.removeFirst();
                System.out.println("Consuming = " + value);
                buffer.notify();
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
        ProducerConsumerBufferLock producerConsumer = new ProducerConsumerBufferLock(bufferSize);

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
