package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

import java.util.LinkedList;

class ProducerConsumerSimpleVala {

    LinkedList<Integer> buffer = new LinkedList<Integer>();
    final int capacity;

    public ProducerConsumerSimpleVala(int capacity) {
        this.capacity = capacity;
    }


    void produce(int limit) throws InterruptedException {

        for(int value=0; value<limit; value++) {
            synchronized(this) {
                System.out.println("Lock Acquired By Producer");
                if(buffer.size()==capacity) {
                    //can't produce more
                    System.out.println();
                    System.out.println("Producer waiting");
                    wait();
                }
                System.out.println("Producing = " + value);
                buffer.add(value);
                //notify();
            }
            Thread.sleep(2000);
        }
        //synchronize on the object
        //wait if capacity is full - can't produce more
        //produce and put into list
        //notify consumer to consume

    }

    void consume(int limit) throws InterruptedException{

        for(int i=0; i<limit; i++) {
            synchronized(this) {
                System.out.println("Lock Acquired By Consumer");
                if(buffer.size()==0) {
                    //can't consume more
                    System.out.println();
                    System.out.println("Consumer waiting");
                    wait();
                }
                int value = buffer.removeFirst();
                System.out.println("Consuming = " + value);
                //notify();
            }
            Thread.sleep(2000);
        }
        //synchronize on the object
        //wait if can't consume anymore - buffer is empty
        //consume and put into list
        //notify prdocuer to produce
    }

}

public class ProducerConsumerSimple {

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 8;
        int limit = 25;
        ProducerConsumerSimpleVala producerConsumer = new ProducerConsumerSimpleVala(bufferSize);

        Thread t = new Thread();
        t.start();
        System.out.println( t.getName());
        t.join();

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
        consumer.start();
        producer.start();

        System.out.println(producer.getName());
        System.out.println(consumer.getName());
        producer.join();
        consumer.join();
    }
}
