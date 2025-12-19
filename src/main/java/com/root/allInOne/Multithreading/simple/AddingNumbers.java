package com.root.allInOne.Multithreading.simple;

import lombok.Getter;

public class AddingNumbers {

    public static void main(String[] args) throws InterruptedException {
        AddingNumbersThread t1 = new AddingNumbersThread(0, Integer.MAX_VALUE);
        AddingNumbersThread t2 = new AddingNumbersThread(0, Integer.MAX_VALUE/2);
        AddingNumbersThread t3 = new AddingNumbersThread(1+Integer.MAX_VALUE/2, Integer.MAX_VALUE);
        long start = System.currentTimeMillis();
        t1.start();
        t1.join();
        System.out.println("Time taken = " +  (System.currentTimeMillis() - start));
        start = System.currentTimeMillis();
        t2.start();
        t3.start();
        t2.join();
        t3.join();
        System.out.println("Time taken = " +  (System.currentTimeMillis() - start));
    }
}

class AddingNumbersThread extends Thread {
    int start, end;
    @Getter
    long sum;
    AddingNumbersThread(int start, int end) {
        this.start = start; this.end = end;
    }
    @Override
    public void run() {
        long runningSum = 0;
        for(int i=start; i<end; i++) {
            runningSum += i;
        }
        this.sum = runningSum;
    }
}
