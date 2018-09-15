package com.ipnet.blservice;

import org.springframework.web.multipart.MultipartFile;

public interface AliService {
    //获取短信验证码
    String sendMessageCode(String telephone);

    //上传图片，得到图片url
    String uploadPicture(String projectID, String filename, String base64);

    //原样上传文件
    String uploadFile( MultipartFile file);

    //原样上传图片
    String uploadImage(MultipartFile file);

    String uploadBase64File(String path,String base64);
}
