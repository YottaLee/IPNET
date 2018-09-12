package com.ipnet.blservice.apiservice;

import java.io.IOException;
import java.util.List;

public interface MoneyMovementService {
    public String retrieveDestacc(String username , String password) throws IOException;
    public String createTransfer(String username , Double transferamount,String password ,String  srcacctId , String payeeId) throws IOException;
    public List<String> CombinationsAccountandPayee(String username , String password) throws IOException;
    public String confirmTransfer(String username , String password , String controlFlowId) throws IOException;
}
