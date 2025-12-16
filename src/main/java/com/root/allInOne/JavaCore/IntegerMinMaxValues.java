package com.root.allInOne.JavaCore;

public class IntegerMinMaxValues {
    public static void main(String[] args) {
        int maxPositive = Integer.MAX_VALUE;
        if(maxPositive + 1 == Integer.MIN_VALUE) {
            System.out.println("adding one to max goes to negative scale from backwards");
        }
        int minNegative = Integer.MIN_VALUE;
        minNegative = minNegative * -1;
        if(minNegative == Integer.MIN_VALUE) {
            System.out.println("negative is same as itself");
        }
        // Integer.MIN_VALUE  is -2^31
        // -(-2^31) is +2^31 = -2^31
        // Bcoz 2^31-1 is the max value of integer so if you add 1 to it, it becomes 2^31 but there is no such thing, it goes to negative scale
        // from backwards soo first element in negative scale from backwards is -2^31


    }
}
