package com.ipnet.bl.api;

import com.ipnet.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class TokenServiceBL {
    @Autowired
    private Token token;


    public String getRealAccessToken(String username , String password) throws IOException{
        String responsebodyString = "";
        return responsebodyString;
    }
}
