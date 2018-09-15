package com.ipnet.bl.TransactionLog;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String args[]){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(new Date());
        System.out.println(date);
    }
}
