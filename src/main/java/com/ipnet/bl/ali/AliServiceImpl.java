package com.ipnet.bl.ali;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ipnet.blservice.AliService;
import com.ipnet.enums.ResultMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

@Service
public class AliServiceImpl implements AliService {

    //向OSS云存储上传
    private static String endPoint;
    private static String oss_accessKeyId;
    private static String oss_accessKeySecret;
    private static String bucketName;
    private static String fileSeparator;

    //使用阿里云短信服务平台
    private static String product;
    private static String domain;
    private static String accessKeyId;
    private static String accessKeySecret;

    static {
        //OSS云存储变量初始化
        endPoint= AliConstant.endpoint;
        oss_accessKeyId= AliConstant.oss_accessKeyId;
        oss_accessKeySecret= AliConstant.oss_accessKeySecret;
        bucketName= AliConstant.bucketName;
        fileSeparator=File.separator;

        //短信平台必要变量初始化
        product=AliConstant.product;
        domain=AliConstant.domain;
        accessKeyId= AliConstant.accessKeyId;
        accessKeySecret= AliConstant.accessKeySecret;
    }

    private IAcsClient acsClient;
    private OSSClient ossClient;

    @Value("${constant.codeLength}")
    private int codeLength;

    public AliServiceImpl(){
        this.initAcs();
        this.initOss();
    }

    private void initAcs(){
        try {
            IClientProfile profile= DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou",product, domain);
            acsClient=new DefaultAcsClient(profile);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    private void initOss(){
        ossClient=new OSSClient(endPoint,oss_accessKeyId,oss_accessKeySecret);
    }

    public String uploadPicture(String projectID,String filename,String base64){
        //截取出图片类型
        int end=base64.indexOf(";base64");
        String type=base64.substring(11,end);
        base64=base64.substring(end+8);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] bytes1 = decoder.decode(base64);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes1);
        int sepatator=filename.lastIndexOf(".");
        return this.uploadStreamToOss(inputStream,projectID+fileSeparator+filename.substring(0,sepatator)+"."+type);
    }

    private String uploadStreamToOss(InputStream inputStream, String fileName) {
        String ret = "";
        String url="";
        try {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);
            //上传文件
            PutObjectResult putResult = ossClient.putObject(bucketName,fileName, inputStream, objectMetadata);
            ret = putResult.getETag();
            url = bucketName+"."+endPoint+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(ret.equals("")){
            return "上传图片失败";
        }else{
            return url;
        }
    }

    public String upLoad(File file){


        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        String dateStr=format.format(new Date());

        // 判断文件
        if(file==null){
            return null;
        }
        OSSClient client=new OSSClient(endPoint, oss_accessKeyId,oss_accessKeySecret);
        try {
            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            String fileUrl = (file.getName());
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                return "https:"+fileSeparator+bucketName+"."+endPoint+fileSeparator+fileUrl;
            }
        } catch (OSSException oe){

        }catch (com.aliyun.oss.ClientException ce){

        }finally{
            if(client!=null){
                client.shutdown();
            }
        }
        return null;
    }

    //判断OSS服务文件上传时文件的contentType
    private String getContentType(String filenameExtension) {
        String suffix=filenameExtension.toLowerCase();
        switch (suffix){
            case ".bmp":
                return "image/bmp";
            case ".gif":
                return "image/gif";
            case ".jpeg":
            case ".jpg":
            case ".png":
                return "image/jpeg";
            case ".html":
                return "text/html";
            case ".txt":
                return "text/plain";
            case ".vsd":
                return "application/vnd.visio";
            case ".pptx":
            case ".ppt":
                return "application/vnd.ms-powerpoint";
            case ".docx":
            case ".doc":
                return "application/msword";
            case ".xml":
                return "text/xml";
            default:
                return "image/jpeg";
        }
    }

    @Override
    public String sendMessageCode(String telephone) {
        try {
            //设置超时时间
            System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
            System.setProperty("sun.net.client.defaultReadTimeout", "10000");

            //组装请求对象
            SendSmsRequest request=new SendSmsRequest();
            //使用post提交
            request.setMethod(MethodType.POST);
            //待发送的手机号
            request.setPhoneNumbers(telephone);
            //短信签名
            request.setSignName("钟洁");
            //短信模板ID
            request.setTemplateCode("SMS_140120279");
            //验证码
            String codetemp=this.generateCode();
            request.setTemplateParam("{\"code\":\""+codetemp+"\"}");

            //SendSmsResponse sendSmsResponse
            acsClient.getAcsResponse(request);
            return codetemp;
        }catch (Exception e){
            return ResultMessage.Fail.toString();
        }
    }

    //随机生成验证码
    private String generateCode(){
        StringBuilder code= new StringBuilder();
        for(int i=0;i<codeLength;i++){
            int random=(int)(Math.random()*10);
            code.append(random);
        }
        return code.toString();
    }



}
