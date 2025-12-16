package com.root.allInOne.AllInterviews.CoinBase;


interface Iterable<T> {
    Iterator<T> iterator();
}

interface Iterator<T> {
    boolean hasNext();
    T next();
}
class MyList<T> implements Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
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



