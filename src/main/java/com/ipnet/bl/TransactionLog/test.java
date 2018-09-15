package com.ipnet.bl.TransactionLog;

import com.ipnet.blservice.TransactionLogService;
import com.ipnet.dao.TransactionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String args[]) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
       String date = df.format(new Date());
       System.out.println(date);
    }
}
