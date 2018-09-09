package com.ipnet.bl.api;

import com.ipnet.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class MoneyMovementServiceBL {
    @Autowired
    private Token token;

    public void  retrieveDestacc(String username , String password) throws IOException{

    }
    public String createTransfer(String username , Double transferamount,String password) throws IOException{
        return "";
    }
    public void confirmTransfer(String username , String password , String controlFlowId) throws IOException{
        return ;
    }
}
