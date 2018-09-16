package com.ipnet.controller;

import com.ipnet.blservice.AccountBLService;
import com.ipnet.utility.IDNotExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AccountController {
    @Autowired
    private AccountBLService service;

    @RequestMapping("/MoneyMove")
    public @ResponseBody boolean MoneyMove(@RequestParam String srcAccount , @RequestParam String destAccount ,@RequestParam double amount ,@RequestParam String paytype , @RequestParam String patentId) throws IDNotExistsException{
         return service.MoneyMove(srcAccount , destAccount ,amount , paytype , patentId);
    }
}
