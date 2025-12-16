package com.root.allInOne.JavaCore;

import java.util.function.Function;

@FunctionalInterface //you add this annotation to ensure only one abstarct method is there in interface
interface InterfaceWithOneAbstractMethod {
    void myAbsMethod();
    default int getNum() {
        return 1;
    }
    static int getYourNum() {
        return 2;
    }
}

//@FunctionalInterface //this gives error - Multiple non-overriding abstract methods found in interface
interface InterfaceWithMultipleAbstractMethod {
    void myAbsMethod();
    void anotherAbsMethod();
    default int getNum() {
        return 1;
    }
    static int getYourNum() {
        return 2;
    }
}
public class UnderstandingLambda {
    static Function<String, Integer> stringVoidFunction = (String s) -> s.length();
    static InterfaceWithOneAbstractMethod inter = () -> System.out.println("inside Interface");

//    static InterfaceWithMultipleAbstractMethod multipleAbstractMethod = () -> System.out.println("inside Interface"); can't be done

    public static void main(String[] args) {
        inter.myAbsMethod();
        inter.getNum();
        InterfaceWithOneAbstractMethod.getYourNum();
        System.out.println(stringVoidFunction.apply("saurabh"));
//        var lambdaMy = () -> 2; lambda expression requires an explicit target type

    }

//    Conclusion: now I know acche is :
//    1. what is funcitonal interface
//    2. lambda can be assigned to an interaface given that it has only one abstract method - bcoz that's what functional interaface means
//    3. so basicallly lambda can be only be assigned to a functional interface (not even to var)
//    4. about use of annotation
//
}
