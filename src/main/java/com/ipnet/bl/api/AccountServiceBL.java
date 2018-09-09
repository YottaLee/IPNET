package com.ipnet.bl.api;

import com.ipnet.blservice.apiservice.AccountService;
import com.ipnet.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class AccountServiceBL implements AccountService{
    @Autowired
    private Token token;
    public String GetAccounts(String username ,String password) throws IOException{
        String responsebodyString = "";
        return responsebodyString;
    }
    public String GetAccountDetails(String username ,String password) throws IOException{
        String responsebodyString = "";
        return responsebodyString;
    }
    public String GetTransaction(String username ,String password) throws IOException{
        String responsebodyString = "";
        return responsebodyString;
    }
}
