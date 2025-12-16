package com.root.allInOne.AllInterviews;

import java.util.*;

public class MinCoins {

    static int len;
    static Map<String, Integer> dp = new HashMap<>();
    static Integer[] arr = new Integer[]{10,7,5,4};
    //fuck I got to know array of premitive ints cant be sorted in a reverse way in java
    //array of Integer can be sorted in reverse

    public static void main(String[] args) {
        len = arr.length;
        Arrays.sort(arr, Collections.reverseOrder());
        int ans = getMinCoins(0, 20);
        if (ans == Integer.MAX_VALUE) {
            ans = -1;
        }
        System.out.println(ans);
        List<Integer> list = new ArrayList<>();
        Collections.sort(list, Integer::compare);
    }

    static int getMinCoins(int index, int value) {
        if (index >= len && value == 0) return 0;
        if (index >= len) return Integer.MAX_VALUE;
         String key = index + "-" + value;
         if (dp.get(key) != null) return dp.get(key);
        int min = Integer.MAX_VALUE;
        int multiplier = 0;
        while (value >= 0) {
            int temp = getMinCoins(index + 1, value);

            min = Math.min(min, temp == Integer.MAX_VALUE ? temp : (multiplier + temp));
            multiplier += 1;
//            value = value - (multiplier * arr[index]); this was the bug, it just needs to be like below
//            fuckkkkkkkkkk
//            frustrationnnnnnnn
            value = value - arr[index];
//            System.out.println("index="+index+"; value="+value+"; min="+min);
        }
        dp.put(key, min);
        return min;
    }


}


