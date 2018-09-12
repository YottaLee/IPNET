package com.ipnet.bl.api;

import com.ipnet.blservice.apiservice.MoneyMovementService;
import com.ipnet.entity.APIConstant;
import lombok.Data;
import org.json.simple.JSONValue;
import org.json.simple.JSONObject;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Data
@Service
public class MoneyMovementServiceBL implements MoneyMovementService{

    private final TokenServiceBL tokenServiceBL;

    @Autowired
    public MoneyMovementServiceBL(TokenServiceBL tokenServiceBL) {
        this.tokenServiceBL = tokenServiceBL;
    }

    public String retrieveDestacc(String username , String password) throws IOException{
        String authorization = "Bearer " + tokenServiceBL.getRealAccessToken(username , password);
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/payees/sourceAccounts?paymentType="+"INTERNAL_DOMESTIC")
                .get()
                .addHeader("authorization", authorization)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", APIConstant.CLIENT_ID)
                .build();

        Response response = client.newCall(request).execute();
        String responseBodyString = response.body().string();
        System.out.println("retrieveDestacc "+responseBodyString);
        return responseBodyString;
    }

    public String createTransfer(String username , Double transferamount , String password , String srcAcctId , String payeeId) throws IOException{
        String authorization = "Bearer " + tokenServiceBL.getRealAccessToken(username , password);
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json");
        // RequestBody body = RequestBody.create(mediaType, "{\"sourceAccountId\":\"355a515030616a53576b6a65797359506a634175764a734a3238314e4668627349486a676f7449463949453d\",\"transactionAmount\":4500.25,\"transferCurrencyIndicator\":\"SOURCE_ACCOUNT_CURRENCY\",\"payeeId\":\"7977557255484c7345546c4e53424766634b6c53756841672b556857626e395253334b70416449676b42673d\",\"chargeBearer\":\"BENEFICIARY\",\"paymentMethod\":\"GIRO\",\"fxDealReferenceNumber\":\"12345678\",\"remarks\":\"lajeluro\",\"transferPurpose\":\"CASH_DISBURSEMENT\"}");
        RequestBody body = RequestBody.create(mediaType, "{\"sourceAccountId\":\""+srcAcctId+"\",\"transactionAmount\":4500.25,\"transferCurrencyIndicator\":\"SOURCE_ACCOUNT_CURRENCY\",\"payeeId\":\""+payeeId+"\",\"chargeBearer\":\"BENEFICIARY\",\"paymentMethod\":\"GIRO\",\"fxDealReferenceNumber\":\"12345678\",\"remarks\":\"lajeluro\",\"transferPurpose\":\"CASH_DISBURSEMENT\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/internalDomesticTransfers/preprocess")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", APIConstant.CLIENT_ID)
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        System.out.println(jsonObject);
        return jsonObject.toString();
    }
    public void confirmTransfer(String username , String password , String controlFlowId) throws IOException{
        OkHttpClient client = new OkHttpClient();

        String authorization = "Bearer " + tokenServiceBL.getRealAccessToken(username , password);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"controlFlowId\":\"45534b7438634c567a566777354c5861486d59616c4665467a624e61724c73574b4c50494f386664306d6f3d\"}");
        // RequestBody body = RequestBody.create(mediaType, "{\"controlFlowId\":\" "+controlFlowId+"\"}");
        Request request = new Request.Builder()
                .url("https://sandbox.apihub.citi.com/gcb/api/v1/moneyMovement/internalDomesticTransfers")
                .post(body)
                .addHeader("authorization", authorization)
                .addHeader("uuid", UUID.randomUUID().toString())
                .addHeader("accept", "application/json")
                .addHeader("client_id", APIConstant.CLIENT_ID)
                .addHeader("content-type", "application/json")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = (JSONObject) JSONValue.parse(response.body().string());
        System.out.println(jsonObject);
    }
}
