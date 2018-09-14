package com.ipnet.entity;

import lombok.Data;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import okhttp3.*;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Data
public class Token {
    private String accessToken ;
    private String eventId ;
    private String bizToken ;
    private String modulus ;
    private String exponent;

    public Token() {
        accessToken = getAccessToken();
        Map<String , String> map = getBizTokenEtc(accessToken);
        eventId = map.get("eventId");
        bizToken = map.get("bizToken");
        modulus = map.get("modulus");
        exponent = map.get("exponent");

    }

    private static String getAccessToken(){
        String accessToken = "";
        try{
        OkHttpClient client = new OkHttpClient();
        String client_id = APIConstant.CLIENT_ID;
        String client_scrent = APIConstant.CLIENT_SCRENT;
        String encode_key = client_id + ":" + client_scrent;
        String authorization = "Basic " + Base64.encodeBase64String(encode_key.getBytes());
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(mediaType, "grant_type=client_credentials&scope=/api");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("authorization", authorization)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        accessToken = (String) jsonObject.get("access_token");
        System.out.println("step1 access_token:");
        System.out.println("\t" + accessToken);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return accessToken;
    }

    private static Map getBizTokenEtc(String accessToken){
        Map<String, String> map = new HashMap<>();
        try {

            OkHttpClient client = new OkHttpClient();
            String client_id = APIConstant.CLIENT_ID;
            String authorization = "Bearer " + accessToken;
            UUID uuid = UUID.randomUUID();
            Request request = new Request.Builder()
                    .url("https://sandbox.apihub.citi.com/gcb/api/security/e2eKey")
                    .get()
                    .addHeader("authorization", authorization)
                    .addHeader("client_id", client_id)
                    .addHeader("uuid", uuid.toString())
                    .addHeader("content-type", "application/json")
                    .build();
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
            String modulus = null;
            String exponent = null;
            String bizToken = null;
            String eventId = null;
            if (jsonObject != null) {
                modulus = (String) jsonObject.get("modulus");
                exponent = (String) jsonObject.get("exponent");
                Headers headers = response.headers();
                bizToken = headers.get("bizToken");
                eventId = headers.get("eventId");
                map.put("modulus", modulus);
                map.put("exponent", exponent);
                map.put("bizToken", bizToken);
                map.put("eventId", eventId);
            }
            System.out.println("step2 map:");
            for (String s : map.keySet()) {
                System.out.println("\tkey:" + s + "\tvalues:" + map.get(s));
            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return map;
    }
}
