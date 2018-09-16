package com.ipnet.controller;

import com.ipnet.blservice.apiservice.MoneyMovementService;
import com.ipnet.entity.Combinations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
public class MoneyMovementController {
      @Autowired
      private MoneyMovementService service;

     @RequestMapping("/retrieveDestacc")
      public @ResponseBody  String retrieveDestacc(@RequestParam String username ,@RequestParam String password) throws IOException{
            return service.retrieveDestacc(username , password);
      }

      @RequestMapping("/createTransfer")
      public @ResponseBody String createTransfer(@RequestParam String username , @RequestParam Double transferamount , @RequestParam String password ,@RequestParam String  srcacctId ,@RequestParam String payeeId) throws IOException{
            return  service.createTransfer(username , transferamount , password , srcacctId , payeeId);
      }

      @RequestMapping("/CombinationsAccountandPayee")
      public @ResponseBody  List<Combinations> CombinationsAccountandPayee(@RequestParam String username , @RequestParam String password) throws IOException{
            return  service.CombinationsAccountandPayee(username , password);
      }

      @RequestMapping("/confirmTransfer")
      public @ResponseBody String confirmTransfer(@RequestParam String username , @RequestParam String password , @RequestParam String controlFlowId) throws IOException{
            return  service.confirmTransfer(username , password , controlFlowId);
      }
}
