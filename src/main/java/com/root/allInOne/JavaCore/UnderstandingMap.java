package com.root.allInOne.JavaCore;

import java.util.*;

class KeyValue {
    String num;
    String deno;

    public KeyValue(String num, String deno) {
        this.num = num;
        this.deno = deno;
    }

    public static KeyValue of(String num, String deno) {
        return new KeyValue(num, deno);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyValue keyValue = (KeyValue) o;
        return Objects.equals(num, keyValue.num) && Objects.equals(deno, keyValue.deno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, deno);
    }
}

record KeyValueRecord (String num, String deno) {
    public static KeyValueRecord of(String num, String deno) {
        return new KeyValueRecord(num, deno);
    }
}

public class UnderstandingMap {
    public static void main(String[] args) {
        Map<List<String>, Integer> map = new HashMap<>();
        map.put(Arrays.asList("a","b"),1);
        map.put(Arrays.asList("b","a"),2);
        System.out.println(map.get(Arrays.asList("a","b")));
        System.out.println(map.get(Arrays.asList("b","a")));

    }
}
