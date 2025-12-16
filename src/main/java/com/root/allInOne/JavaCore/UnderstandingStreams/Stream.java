package com.root.allInOne.JavaCore.UnderstandingStreams;

import com.root.allInOne.JavaCore.UnderstandingStreams.domain.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stream {
/*
    Given two entity
    Customer:
    String name;
    String email;
    List allAddress;

    Address:
    String city;
    String pincode;

    Use stream api to fetch all cities from all users(not necessarily distinct), interviewer was interested in knowing how can we nest streams.*/
    public List<String> getCitiesFromAllUsers(List<Customer> users) {
       return users.stream().map(user -> user.getAllAddress()).flatMap(list -> list.stream()).map(address ->  address.getCity()).collect(Collectors.toList());
    }

    Map<String, Integer> countOfCharactersUsingJavaStream(String s) {
        return Arrays.stream(s.split("")).collect(Collectors.toMap(k->k, v->1, (oldValue, newValue) -> oldValue+newValue));
    }


}
