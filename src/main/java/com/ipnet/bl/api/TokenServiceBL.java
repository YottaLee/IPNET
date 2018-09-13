package com.ipnet.bl.api;

import com.ipnet.blservice.apiservice.TokenService;
import com.ipnet.entity.APIConstant;
import com.ipnet.entity.Token;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;
@Service
public class TokenServiceBL implements TokenService{

    private Token token = new Token();

    public String getRealAccessToken(String username , String password) throws IOException{
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        System.err.println("bizToken: "+token.getBizToken());
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        System.out.println(password);
        UUID uuid = UUID.randomUUID();
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=password&scope=/api&username="+username+"&password="+password);
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("bizToken", token.getBizToken())
                .addHeader("uuid", uuid.toString())
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("accept", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        String realAccessToken = (String) jsonObject.get("access_token");
        System.out.println("step3 real_access_token:");
        System.out.println("\t" + realAccessToken);
        return realAccessToken;
    }
}
