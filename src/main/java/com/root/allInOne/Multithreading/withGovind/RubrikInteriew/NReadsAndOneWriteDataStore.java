package com.root.allInOne.Multithreading.withGovind.RubrikInteriew;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//To be used read operation doesn't change the state of the data store

/*
* Why not use this for a standard Bounded Queue?
In a standard ArrayBlockingQueue, a put() operation changes the tail and a take() operation changes the head.
* Since both operations modify the state of the queue, they both require a Write Lock.
Using a ReadWriteLock on a queue would only be beneficial if you were frequently calling a contains() or size() method without removing elements.
If 90% of your operations are add or remove, a standard ReentrantLock is actually faster because
* ReadWriteLock has more internal overhead to track the number of active readers.
*/

class ReadWriteDataStore {
    private final List<String> data = new ArrayList<>();
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void readData(int index) {
        readLock.lock();
        try {
            if (index < data.size()) {
                System.out.println(Thread.currentThread().getName() + " is reading: " + data.get(index));
                // Simulate processing time
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
        }
    }

    public void addData(String value) {
        writeLock.lock();
        try {
            System.out.println("\n--- " + Thread.currentThread().getName() + " is WRITING: " + value + " ---");
            data.add(value);
            // Writers usually take longer or need exclusive access
            Thread.sleep(2000);
            System.out.println("--- " + Thread.currentThread().getName() + " finished writing ---\n");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }
}

public class NReadsAndOneWriteDataStore {
    public static void main(String[] args) throws InterruptedException {
        ReadWriteDataStore store = new ReadWriteDataStore();

        // Pre-fill with one item so readers have something to look at
        store.addData("Initial Item");

        // We'll use a thread pool to manage our readers and writers
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 1. Start multiple Reader threads
        // Notice in the console: these will likely print at the same time!
        Runnable readerTask = () -> {
            for (int i = 0; i < 2; i++) {
                store.readData(0);
            }
        };

        // 2. Start a Writer thread
        Runnable writerTask = () -> {
            store.addData("New Tech Stack Update");
        };

        // Execute 3 readers and 1 writer
        executor.execute(readerTask); // Reader 1
        executor.execute(readerTask); // Reader 2
        executor.execute(readerTask); // Reader 3

        Thread.sleep(500); // Small delay to ensure readers start first
        executor.execute(writerTask); // The Writer

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}
