package com.root.allInOne.DailyQuestions;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstringNoRepeat {

    static int max = 0;
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcdefgczxabcdefghij"));
    }

    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int len=s.length();
        int start=0, j=0;
        while(j<len) {
            for(j=start; j<len; j++) {
                Character c = s.charAt(j);
                if(map.containsKey(c)) {
                    System.out.println("c="+c);
                    System.out.println("j="+j);
                    System.out.println("start="+start);

                    max = Math.max(max, j-start);
                    System.out.println("max="+max);
                    System.out.println();
                    start = map.get(c)+1;
                    map.clear();
                    break;
                } else {
                    map.put(c, j);
                }
            }

            if(j==len) {
                max = Math.max(max, j-start);
            }

        }
        return max;
    }

}
