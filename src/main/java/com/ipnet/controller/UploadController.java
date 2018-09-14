package com.ipnet.controller;

import com.ipnet.blservice.AliService;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private AliService aliService;

    /**
     * 上传文件到OSS服务器
     * @param path 文件的完整路径，即文件夹/文件名，e.g.ipnet/logo.jpg
     * @param file 要上传的文件
     * @return 上传的文件的url
     */
    @RequestMapping("/file")
    public @ResponseBody
    String uploadFile(@RequestParam String path, @RequestBody MultipartFile file) throws IOException{
//        byte[] buffer=file.getBytes();
//        Base64.Encoder encoder=Base64.getEncoder();
//        String re=encoder.encodeToString(buffer);
        return aliService.uploadFile(path,file);
    }

    /**
     * 上传文件到OSS服务器
     * @param path 文件的完整路径，即文件夹/文件名，e.g.ipnet/logo.jpg
     * @param base64 要上传的文件的base64编码
     * @return 上传的文件的url
     */
    @RequestMapping("/base64")
    public @ResponseBody
    String uploadFile(@RequestParam String path, @RequestParam String base64){
        //System.out.println("path:"+path);
        //System.out.println("fileName:"+file.getOriginalFilename());
        return aliService.uploadBase64File(path,base64);
    }

    /**
     * 上传专利图片
     * @param file
     * @return
     */
    @RequestMapping("/image")
    public @ResponseBody
    String uploadImage(@RequestBody MultipartFile file){
        System.out.println("Success upload image!!!!!!!!!!!!!!!!!!! ");
        return null;
    }

}
