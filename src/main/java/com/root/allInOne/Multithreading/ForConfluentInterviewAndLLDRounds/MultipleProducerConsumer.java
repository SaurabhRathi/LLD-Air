package com.root.allInOne.Multithreading.ForConfluentInterviewAndLLDRounds;

/*
There are 2 places
1. waitzone - for people who couldn't acquire the lock, all with same priority
2. wait()queue or pause queue - jinhone wait() call kiya tha
so agar kisine wait() call kiya tha vo 2nd vale me jayega and agar kisi aur ne notify() call nahi kiya to vo paused hi rahega
matlab yahi fasa rahega

Imagine it as, many producers and consumers trying to acquire the lock, but only 1 could acquire
those who couldn't are added into waitlist.

---------------------------------

let's say starting me hi, ek consumer acquries it first, so starting me since buffer size is zero, that thread will call wait
and vo VAPAS WAIT LIST ME ADD HO JAYEGA
but one thing is he will wait on a different line number, he will wait in if condition jab ki others are waiting on start of code

so any point of time pe, different line number pe log wait kar sakte hai

---------------------------------

so producer1 aaya, produce kiya notify() call kiya to most eligible thread wait list se bahar aagaya
so currently 2 threads will try to acquire, proucer1 and one more
such thing keeps on happening

---------------------------------

for the multiple producer consumer problem - we can do notifyAll, but notify still works, why?
In Java multithreading, when a thread calls the "notify" method on an object,
it sends a notification to only one thread that is currently waiting on that object's monitor;
which thread is woken up is determined by the operating system's thread scheduling mechanism.

So rather than waking up all the threads, and wasting energy and time, let OS decide which to wake up

---------------------------------

if a thread starts waiting due to wait() can other thread enter synchronized block, although notify is not called by that thread?
Yes, if a thread starts waiting inside a synchronized block (e.g., by calling wait()),
another thread can enter the synchronized block, provided it acquires the lock on the same object.

So threads can wait on 2 scenarios:
1. synchronized(lock)
2. wait()

So basically by calling wait() it is releasing the lock ----- FFFUUUCCKKK

Here's how it works:
Acquiring the Lock:
When a thread enters a synchronized block, it acquires the lock associated with the object on which the block is synchronized.
Releasing the Lock:
When a thread calls wait() inside a synchronized block, it releases the lock and enters a waiting state.
Other Threads Can Enter:
While the first thread is waiting, another thread can acquire the lock and enter the synchronized block.
Notifying Waiting Threads:
When a thread calls notify() or notifyAll() inside a synchronized block, it signals to one or all waiting threads that they can wake up and try to acquire the lock again.

public class WaitNotifyExample {

    public static void main(String[] args) {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 1: Entering synchronized block");
                try {
                    System.out.println("Thread 1: Waiting...");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Exiting synchronized block");
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Thread 2: Entering synchronized block");
                System.out.println("Thread 2: Notifying thread 1...");
                lock.notify();
                System.out.println("Thread 2: Exiting synchronized block");
            }
        });

        thread1.start();
        thread2.start();
    }
}

In this example, when thread1 calls wait(), it releases the lock on lock.
This allows thread2 to acquire the lock and enter the synchronized block. thread2 then calls notify(), which wakes up thread1.
Finally, thread1 re-acquires the lock and continues execution.
---------------------------------

Also, one more thing which is different in multiple producer consumer than single producer consumer is
there is while loop instead of if
kyuki single me pata hai ki agar koi if condition me aake wait kar raha to, notify pe usi line se shuru hoga
lekin notify karne pe agar same category vala notify hua to vapas wait karna pad sakta hai hence while loop

*/


//https://www.javamadesoeasy.com/2018/06/how-to-solve-multiple-producer-consumer.html
//multiple consumer and producer problem using wait() and notify() method
import java.util.LinkedList;
import java.util.List;


/**
 * Producer Class.
 */
class Producer implements Runnable {

    private List<Integer> sharedQueue;
    private int maxSize=4; //maximum number of products which sharedQueue can hold at a time.
    int productionSize=5; //Total no of items to be produced by each producer
    int producerNo;

    public Producer(List<Integer> sharedQueue, int producerNo) {
        this.sharedQueue = sharedQueue;
        this.producerNo = producerNo;
    }

    @Override
    public void run() {
        for (int i = 1; i <= productionSize; i++) { //produce products.
            try {
                produce(i);
            } catch (InterruptedException e) {  e.printStackTrace(); }
        }
    }

    private void produce(int i) throws InterruptedException {

        synchronized (sharedQueue) {
            //if sharedQuey is full wait until consumer consumes.
            while (sharedQueue.size() == maxSize) {
                System.out.println(Thread.currentThread().getName()+", Queue is full, producerThread is waiting for "
                        + "consumerThread to consume, sharedQueue's size= "+maxSize);
                sharedQueue.wait();
            }

            //Bcz each producer must produce unique product
            //Ex= producer0 will produce 1-5  and producer1 will produce 6-10 in random order
            int producedItem = (productionSize*producerNo)+ i;

            System.out.println(Thread.currentThread().getName() +" Produced : " + producedItem);
            sharedQueue.add(producedItem);
            Thread.sleep((long)(Math.random() * 1000));
            sharedQueue.notify();
        }
    }
}

/**
 * Consumer Class.
 */
class Consumer implements Runnable {
    private List<Integer> sharedQueue;
    public Consumer(List<Integer> sharedQueue) {
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                consume();
                Thread.sleep(100);
            } catch (InterruptedException e) {  e.printStackTrace(); }
        }
    }

    private void consume() throws InterruptedException {

        synchronized (sharedQueue) {
            //if sharedQuey is empty wait until producer produces.
            while (sharedQueue.size() == 0) {
                System.out.println(Thread.currentThread().getName()+", Queue is empty, consumerThread is waiting for "
                        + "producerThread to produce, sharedQueue's size= 0");
                sharedQueue.wait();
            }

            Thread.sleep((long)(Math.random() * 2000));
            System.out.println(Thread.currentThread().getName()+", CONSUMED : "+ sharedQueue.remove(0));
            sharedQueue.notify();
        }
    }

}
public class MultipleProducerConsumer {

    public static void main(String args[]) {
        List<Integer> sharedQueue = new LinkedList<Integer>(); //Creating shared object

        Producer producer0=new Producer(sharedQueue, 0);
        Consumer consumer0=new Consumer(sharedQueue);

        Thread producerThread0 = new Thread(producer0, "ProducerThread0");
        Thread consumerThread0 = new Thread(consumer0, "ConsumerThread0");
        producerThread0.start();
        consumerThread0.start();

        System.out.println("MID");

        Producer producer1=new Producer(sharedQueue, 1);
        Consumer consumer1=new Consumer(sharedQueue);

        Thread producerThread1 = new Thread(producer1, "ProducerThread1");
        Thread consumerThread1 = new Thread(consumer1, "ConsumerThread1");
        producerThread1.start();
        consumerThread1.start();
    }

}


/*OUTPUT

MID
ProducerThread0 Produced : 1
ProducerThread0 Produced : 2
ConsumerThread1, CONSUMED : 1
ProducerThread1 Produced : 6
ProducerThread1 Produced : 7
ProducerThread1 Produced : 8
ProducerThread1, Queue is full, producerThread is waiting for consumerThread to consume, sharedQueue's size= 4
ConsumerThread0, CONSUMED : 2
ProducerThread1 Produced : 9
ProducerThread1, Queue is full, producerThread is waiting for consumerThread to consume, sharedQueue's size= 4
ConsumerThread0, CONSUMED : 6
ConsumerThread1, CONSUMED : 7
ProducerThread0 Produced : 3
ProducerThread0 Produced : 4
ProducerThread0, Queue is full, producerThread is waiting for consumerThread to consume, sharedQueue's size= 4
ConsumerThread1, CONSUMED : 8
ConsumerThread0, CONSUMED : 9
ProducerThread1 Produced : 10
ConsumerThread0, CONSUMED : 3
ConsumerThread1, CONSUMED : 4
ProducerThread0 Produced : 5
ConsumerThread1, CONSUMED : 10
ConsumerThread0, CONSUMED : 5
ConsumerThread1, Queue is empty, consumerThread is waiting for producerThread to produce, sharedQueue's size= 0
ConsumerThread0, Queue is empty, consumerThread is waiting for producerThread to produce, sharedQueue's size= 0

*/