package com.root.allInOne.Multithreading;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class Futures {


    public static void main(String[] args) {
        List<CompletableFuture<Integer>> futuresList = new ArrayList<CompletableFuture<Integer>>();

        CompletableFuture<Integer> addAsy = CompletableFuture.supplyAsync(() -> (addFun1(10, 5)));
        CompletableFuture<Integer> subAsy = CompletableFuture.supplyAsync(() -> (addFun1(15, 5)));
        CompletableFuture<Integer> mulAsy = CompletableFuture.supplyAsync(() -> (addFun1(20, 5)));
        CompletableFuture<String> stringAsy = CompletableFuture.supplyAsync(() -> ("saurabh"));

        futuresList.add(addAsy);
        futuresList.add(subAsy);
        futuresList.add(mulAsy);

        CompletableFuture<Void> allFuturesAsArray =  CompletableFuture.allOf(addAsy, subAsy, mulAsy, stringAsy);
        CompletableFuture<Void> allFutures = CompletableFuture
                .allOf(futuresList.toArray(new CompletableFuture[futuresList.size()]));

        CompletableFuture<List<Integer>> allCompletableFuture = allFutures.thenApply(future ->
                futuresList
                        .stream()
                        .map(completableFuture -> completableFuture.join())
                        .collect(Collectors.toList()));

        CompletableFuture<List<Integer>> completableFuture = allCompletableFuture.toCompletableFuture();

        try {
            List<Integer> finalList = completableFuture.get();
            System.out.print(finalList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static Integer addFun1(int a, int b) {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            System.out.print(Thread.currentThread().getName() + i);
        }
        return a + b;
    }

    public static void practicingFutures() {
        CompletableFuture<Integer> addFuture = CompletableFuture.supplyAsync(() -> addFun1(1,2));
        CompletableFuture<Integer> subFuture = CompletableFuture.supplyAsync(() -> addFun1(2,3));
        List<CompletableFuture<Integer>> allFuturesList = Arrays.asList(addFuture, subFuture);
        CompletableFuture<Void> joinedFutures = CompletableFuture.allOf(allFuturesList.toArray(new CompletableFuture[0]));
        joinedFutures.join();


    }
}
