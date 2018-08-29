package com.ipnet.blservice;


public interface AliService {
    //获取短信验证码
    String sendMessageCode(String telephone);

    //上传图片，得到图片url
    String uploadPicture(String projectID, String filename, String base64);
}
