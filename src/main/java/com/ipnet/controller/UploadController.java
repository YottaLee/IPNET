package com.ipnet.controller;

import com.ipnet.blservice.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private AliService aliService;

    /**
     * 上传文件到OSS服务器
     * @param path 文件夹名字，末尾不需要文件分隔符
     * @param file 要上传的文件
     * @return 上传的文件的url
     */
    @RequestMapping("/file")
    public @ResponseBody
    String uploadFile(String path, MultipartFile file){
        return aliService.uploadFile(path,file);
    }

}
