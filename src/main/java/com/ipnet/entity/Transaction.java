package com.ipnet.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Transaction {
    private String payeeId;
    private ArrayList<String> accountIds;
}
