package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;

class ProducerConsumerUsingSemaphore {

    Semaphore semaphore = new Semaphore(1);
    LinkedList<Integer> buffer = new LinkedList<Integer>();
    final int capacity;
    public ProducerConsumerUsingSemaphore(int capacity) {
        this.capacity = capacity;
    }
    void produce(int limit) throws InterruptedException {
        for(int value=0; value<limit; ) {
//            if(semaphore.tryAcquire()) { //no waiting, acquires immediately and return true (hence little unfair to other threads)
                                            //return false and doesn't wait if not available
                semaphore.acquire(); //acquires if available or waits till someone releases or this thread is interrupted
                System.out.println("are threads waiting for producer? = " + semaphore.getQueueLength()); //should return 0 always since consumer using tryAcquire
                if(buffer.size()<capacity) {
                    System.out.println("Producing = " + value);
                    buffer.add(value);
                    value++;
                }
                semaphore.release();
//            }
        }
    }

    void consume(int limit) throws InterruptedException{

        for(int i=0; i<limit; ) {
            if(semaphore.tryAcquire()) {
                System.out.println("are threads waiting for consumer? = " + semaphore.getQueueLength());
                if(buffer.size()>0) {
                    int value = buffer.removeFirst();
                    System.out.println("Consuming = " + value);
                    i++;
                }
                semaphore.release();
            }
        }
    }

}
public class ProducerConsumerSemaphore {

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 8;
        int limit = 25;
        ProducerConsumerUsingSemaphore producerConsumer = new ProducerConsumerUsingSemaphore(bufferSize);

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
