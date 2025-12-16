package com.root.allInOne.JavaCore;

import java.util.stream.IntStream;



class Increment {
    private int count = 100;

    public void increment() {
        count += 1;
    }

    public int getCount() {
        return this.count;
    }
}

public class IncrementInLambda {
    public static void main(String[] args) {
        Increment increment = new Increment();
        int i = 100;
//        IntStream.rangeClosed(0,2).forEach(x -> System.out.println(i++));
//        this doesn't work
        IntStream.rangeClosed(0, 2).forEach(x -> {
            increment.increment();
            System.out.println(increment.getCount());
        });
    }
}
