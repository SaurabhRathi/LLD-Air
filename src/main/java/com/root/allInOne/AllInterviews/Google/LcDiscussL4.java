package com.root.allInOne.AllInterviews.Google;

/*
Given a stream of float values, find the mean of last n values. -> linkedlist
Follow up - Given a value k, find the mean of last n values excluding k highest values from the n values.
1 4 8 3 6 2 4 1

n = 8
k = 3
8-6-4
*/

import java.util.*;

public class LcDiscussL4 {
    //Given a stream of float values, find the mean of last n values. -> linkedlist
    class StreamAvgQuestion {
        Iterator<Float> iterator;
        Deque<Float> deque = new LinkedList<>();
        int n = 0; float sum = 0;
        StreamAvgQuestion(Iterator<Float> iterator, int n) {
            this.iterator = iterator; this.n = n;
        }
        void takeValue(Iterator<Float> iterator) {
            if(!iterator.hasNext()) return; //throw exception
            if( deque.size() >= n ) sum -= deque.removeFirst();
            float num = iterator.next(); deque.add(num); sum += num;
        }

        float getAvg() { return sum/deque.size(); }

        void followUpTakeValue(Iterator<Float> iterator) {

        }
    }

    //You are given a stream of messages and you need to print the messages only if message doesn't appear in the last 5 secs
    class SujeethQuestion {
        void printIfItIsNotInLas5SEC
    }

}




