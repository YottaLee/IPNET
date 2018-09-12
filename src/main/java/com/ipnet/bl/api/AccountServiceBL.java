package com.ipnet.bl.api;

import com.ipnet.blservice.apiservice.AccountService;
import com.ipnet.entity.APIConstant;
import com.ipnet.entity.Token;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.UUID;

public class AccountServiceBL implements AccountService{

    @Autowired
    private TokenServiceBL service;

    public String GetAccounts(String username ,String password) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + service.getRealAccessToken(username , password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println("step4 accounts:");
        System.out.println("\t"+responseBodyString);
        return responseBodyString;
    }
    public String GetAccountDetails(String username ,String password , String accountId) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + service.getRealAccessToken(username , password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts/"+accountId)
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println("step5 account details:");
        System.out.println("\t"+responseBodyString);
        return responseBodyString;
    }
    public String GetTransaction(String username ,String password , String accountId) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String authorization = "Bearer " + service.getRealAccessToken(username,password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/accounts/"+accountId+"/transactions")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .addHeader("client_id", client_id)
                .build();
        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println("step6 transaction details:");
        System.out.println("\t"+responseBodyString);
        return responseBodyString;
    }
}
