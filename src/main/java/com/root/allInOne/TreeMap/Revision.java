//package com.root.allInOne.TreeMap;
//
//import java.util.*;
//import java.util.stream.Stream;
//
//public class Revision {
//
//    static List<Integer> ans = new LinkedList<>();
//    static TreeMap<Integer, List<Integer>> treeMap = new TreeMap<Integer, List<Integer>>();
//
//    public static void main(String[] args) {
//
//        // 1 2 3 4 5 5 6 6 7 8 9 - these elements, but insert in random order
//        //
//
//        int x = 2;
//
//        add();
//        treeMap.forEach((key, value) -> {
//            x=2;
//            for (int val : value) {
//                ans.add(val);
//            }
//        });
//
//        SortedMap<Integer, List<Integer>> x = treeMap.tailMap(1, true);
//
//    }
//
//    static void add() {
//        List<Integer> list = treeMap.getOrDefault(1, new LinkedList<>());
//        list.addAll(Arrays.asList(1, 2, 3));
//        treeMap.put(1, list);
//        foo(ans.stream().parallel());
//    }
//
//    static void foo(Stream<Integer> stream) {
//
//    }
//
//}
