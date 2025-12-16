package com.root.allInOne.Multithreading.printInOrder;



import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class FooVolatile {
    volatile int i=0;
    public FooVolatile() {
    }
    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        i++;
    }
    public void second(Runnable printSecond) throws InterruptedException {
        while(i<=0) {;}
        printSecond.run();
        i++;
    }
    public void third(Runnable printThird) throws InterruptedException {
        while(i<=1) {;}
        printThird.run();
    }
}

class FooSemaphore extends Thread {

    ReentrantLock reentrantLock = new ReentrantLock();

    Semaphore forSecond = new Semaphore(1);
    public FooSemaphore() {
        this.reentrantLock.lock();
    }

    @Override
    public void run() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}


public class volatileSolution {
    public static void main(String[] args) {


    }
}
