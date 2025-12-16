package com.root.allInOne.AllInterviews.CoinBase;
import java.util.*;
import java.util.stream.Collectors;

class Transaction {
 int getId() {return 0;}
}

public class Pagination {

    List<Transaction> list = new ArrayList<>();
    Comparator<Transaction> byId = (a,b) -> a.getId() - b.getId();

    void offsetBased() { //means rowNum based
        int offset = 1000;
        list.stream().skip(offset).limit(10).collect(Collectors.toList());
        list.stream().skip(offset).limit(pageSize).collect(Collectors.toList());
    }

    void sort() {
        list.sort(byId);
        Collections.sort(list, byId);
    }

    void cursorBased() { //means value based
        int cursor = 60;
        int limit = 10;
        List<Transaction> transactions = new ArrayList<>();
        List<Transaction> page = getPage(transactions, cursor, limit);
        cursor = page.size() == 0 ? cursor : page.get(page.size() - 1).getId() ;
    }

    public List<Transaction> getPage(List<Transaction> list, int cursor, int limit) {
        return list.stream()
                .filter(item -> item.getId() > cursor)
                .limit(limit)
                .collect(Collectors.toList());
    }
}
