package com.root.allInOne.LeetCode.JavaQuestions;

import java.util.*;

public class GenericModifiedIterator <T> implements Iterator<T> {
    private LinkedList<T> list = new LinkedList<>(); //doubly linked list
    public GenericModifiedIterator(Iterator<T> iterator) {
        while(iterator.hasNext()) {
            list.add(iterator.next());
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public T peek() {
        return list.peek();
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public T next() {
        return list.pollFirst();
    }

    @Override
    public boolean hasNext() {
        return list.size() > 0;
    }
}

class Solution {
    public static void main(String[] args) {
        Iterator<Integer> iterator = Arrays.asList(1,2,3).iterator();
        GenericModifiedIterator<Integer> genericModifiedIterator = new GenericModifiedIterator<>(iterator);
        genericModifiedIterator.peek();
        genericModifiedIterator.hasNext();
        genericModifiedIterator.next();
    }
}