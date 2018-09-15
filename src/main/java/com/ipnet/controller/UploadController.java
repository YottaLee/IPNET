package com.ipnet.controller;

import com.ipnet.blservice.AliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("upload")
public class UploadController {

    @Autowired
    private AliService aliService;

    /**
     * 上传文件到OSS服务器
     * @param file 要上传的文件
     * @return 上传的文件的url
     */
    @RequestMapping("file")
    public @ResponseBody
    String uploadFile(@RequestBody MultipartFile file) {
        return aliService.uploadFile(file);
    }

    /**
     * 上传专利图
     * @param file
     * @return String
     */
    @RequestMapping("image")
    public @ResponseBody
    String uploadImage(@RequestBody MultipartFile file) {
        System.out.println("Success upload image!!!!!!!!!!!!!!!!!!! ");
        return aliService.uploadImage(file);
    }

}
