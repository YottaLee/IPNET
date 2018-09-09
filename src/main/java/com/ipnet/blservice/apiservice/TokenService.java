package com.ipnet.blservice.apiservice;

import java.io.IOException;

public interface TokenService {
    public String getRealAccessToken(String username , String password) throws IOException;
}
