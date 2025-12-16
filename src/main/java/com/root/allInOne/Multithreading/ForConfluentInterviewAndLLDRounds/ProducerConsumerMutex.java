package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

//mutex = allows only one thread to enter critical section
//semaphore = allows fixed number of threads to enter critical section at any point of time

class ProducerConsumerUsingMutex {

    ReentrantLock mutex = new ReentrantLock();
    LinkedList<Integer> buffer = new LinkedList<Integer>();
    final int capacity;
    public ProducerConsumerUsingMutex(int capacity) {
        this.capacity = capacity;
    }
    void produce(int limit) throws InterruptedException {
        for(int value=0; value<limit; ) {
                if(mutex.tryLock()) {
                    if(buffer.size()<capacity) {
                        System.out.println("Producing = " + value);
                        buffer.add(value);
                        value++;
                    }
                    mutex.unlock();
                }
        }
    }

    void consume(int limit) throws InterruptedException{

        for(int i=0; i<limit; ) {
            if(mutex.tryLock()) {
                if(buffer.size()>0) {
                    int value = buffer.removeFirst();
                    System.out.println("Consuming = " + value);
                    i++;
                }
                mutex.unlock();
            }
        }
    }

}

public class ProducerConsumerMutex {

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 8;
        int limit = 25;
        ProducerConsumerUsingMutex producerConsumer = new ProducerConsumerUsingMutex(bufferSize);

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
