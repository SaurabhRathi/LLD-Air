package com.root.allInOne.JavaGenerics;

public class GenericsUsage {

    interface List<T> {
        void add(int i);
        void add(T i);
    }
    class MyList<T> implements List<T> {
        public void add(int i) {
            System.out.println("primitive");
        }
        public void add(T i) {
            System.out.println("Object");
        }
    }

    class MyListNew<A,B,C> implements List<A> {
        List<C> process(A a, B b) {
            return new MyList<C>();
        }

        List<A> processIt(A a, B b) {
            return new MyList<A>();
        }

        List<A> processItNow(B a, C b) {
            return new MyList<A>();
        }

        public void add(int i) {}
        public void add(A i) {}
    }

    // Jaise tum use kaise karte ho?
    // List<T> list = new ArrayList<T>();
    // to tumko interface and class define karte vakt bhi aise hi define karna hai ... naam ke baju me hi <T> ye likhna hai

    void Driver() {
        List<Integer> list = new MyList<>();
        list.add(2);
        list.add(Integer.valueOf(2));
    }

    public static void main(String[] args) {
        GenericsUsage genericsUsage  = new GenericsUsage();
        genericsUsage.Driver();
    }
    //https://chatgpt.com/c/683aab43-ced4-8009-a6df-d19776b48b95
    //saurabhrathi1908@gmail.com -> chatgpt account
}
