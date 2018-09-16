package com.ipnet.controller;

import com.ipnet.blservice.TransactionLogService;
import com.ipnet.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/TransationLog")
public class TransactionLogController {
      @Autowired
      private TransactionLogService service;

      @RequestMapping("/addTransactionLog")
      public @ResponseBody void addTransactionLog(@RequestParam String buyer,@RequestParam String seller, @RequestParam String buyer_bank_account,@RequestParam String seller_bank_account,@RequestParam String patentId ,@RequestParam double amount ,@RequestParam int IPPoint) {
          service.addTransactionLog(buyer, seller, buyer_bank_account,seller_bank_account,patentId,amount,IPPoint);

      }

      @RequestMapping("/getAllTransactions")
      public @ResponseBody
       List<Transaction> getAllTransactions() {
          System.out.println(service.getAllTransactions().get(0).getAmount());
          return service.getAllTransactions();
      }
}
