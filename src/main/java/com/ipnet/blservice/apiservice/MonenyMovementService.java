package com.ipnet.blservice.apiservice;

import java.io.IOException;

public interface MonenyMovementService {
    public void  retrieveDestacc(String username , String password) throws IOException;
    public String createTransfer(String username , Double transferamount,String password) throws IOException;
    public void confirmTransfer(String username , String password , String controlFlowId) throws IOException;
}
