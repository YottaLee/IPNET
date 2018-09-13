package com.ipnet.blservice.apiservice;

import java.io.IOException;

public interface AccountService {
        public String GetAccounts(String username ,String password) throws IOException;
        public String GetAccountDetails(String username ,String password , String accountId) throws IOException;
        public String GetTransaction(String username ,String password , String accountId) throws IOException;
}
