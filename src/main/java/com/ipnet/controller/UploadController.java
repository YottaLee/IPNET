package com.ipnet.controller;

import com.ipnet.blservice.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    String uploadFile(@RequestParam String path, @RequestBody MultipartFile file){
        //System.out.println("path:"+path);
        //System.out.println("fileName:"+file.getOriginalFilename());
        return aliService.uploadFile(path,file);
    }

    /**
     * 上传专利图片
     * @param file
     * @return
     */
    @RequestMapping("/image")
    public @ResponseBody
    String uploadImage(MultipartFile file){
        System.out.println("Success upload image!!!!!!!!!!!!!!!!!!! ");
        return null;
    }

}
