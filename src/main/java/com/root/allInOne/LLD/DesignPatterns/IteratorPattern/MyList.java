package com.root.allInOne.LLD.DesignPatterns.IteratorPattern;
interface Iterator<T> {
    boolean hasNext();
    T next();
}
interface Iterable<T> {
    Iterator<T> iterator();
}
public class MyList<T> implements Iterable<T>{
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    class MyListIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public T next() {
            return null;
        }
    }
}
