package com.root.allInOne.JavaCore.UnderstandingStreams.domain;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    String name;
    String email;
    List<Address> allAddress;
}
