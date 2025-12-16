package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;
import java.util.Random;

class ProducerConsumerRandomPC {
    final int capacity;
    LinkedList<Integer> buffer = new LinkedList<>();

    Random random = new Random();

    public ProducerConsumerRandomPC(int capacity) {
        this.capacity = capacity;
    }
    void produce(int limit) throws InterruptedException {
        for (int value = 0; value < limit; ) {
            synchronized (this) {
                while (buffer.size() >= capacity) {
                    //can't produce more
                    System.out.println();
                    System.out.println("Producer waiting, buffer size is full");
                    wait();
                }
                int temp = 7;
                int times = Math.max(random.nextInt(), 5)%temp;
                System.out.println();
                System.out.println("Will Produce for " + times + " times");

                for(int i=0; i<times && buffer.size()<capacity && value<limit; i++) {
                    System.out.println("Producing value = " + value + " Buffer size = " + buffer.size());
                    buffer.add(value++);
                }
                notify();
            }
            Thread.sleep(1000);
        }
        notifyAll();

        //synchronize on the object
        //wait if capacity is full - can't produce more
        //produce and put into list
        //notify consumer to consume
    }

    void consume(int limit) throws InterruptedException {
        for (int i = 0; i < 50; i++) {
            synchronized (this) {
                if (buffer.size() == 0) {
                    //can't consume more
                    System.out.println();
                    System.out.println("Consumer waiting, buffer size is zero");
                    wait();
                }
                int times = Math.max(random.nextInt(),0)%5;
                System.out.println();
                System.out.println("Will Consume for " + times + " times");

                for(int c=0; c<times; c++) {
                    if(buffer.size()==0) break;
                    System.out.println("Consuming = " + buffer.peekFirst() + " Buffer size = " + buffer.size());
                    buffer.removeFirst();
                }

                notify();
            }
            Thread.sleep(1000);
        }
        notifyAll(); //if someone is still waiting


        //synchronize on the object
        //wait if can't consume anymore - buffer is empty
        //consume and put into list
        //notify prdocuer to produce
    }
}
public class ProducerConsumerRandomNumProduceConsume {

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 8;
        int limit = 25;
        ProducerConsumerRandomPC producerConsumer = new ProducerConsumerRandomPC(bufferSize);

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
