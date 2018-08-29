package com.ipnet.controller.communitycontroller;

import com.ipnet.bl.ali.AliServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private AliServiceImpl aliService;

    @RequestMapping(value = "/changeBaseToUrl")
    public @ResponseBody String test(@RequestParam String base64, @RequestParam String filename, @RequestParam String projectID){
        return aliService.uploadPicture(projectID,filename+".jpg",base64);
    }

    @RequestMapping(value = "/uploadHtml")
    public @ResponseBody
    ArrayList<String> uploadHtml(MultipartFile zipFile, String projectID){
//        return ossClientUtil.uploadFile(projectID);
        return null;
    }

    @RequestMapping("/toUpLoadFile")
    public String toUpLoadFile(){
        return "test";
    }


    @RequestMapping(value = "/uploadFile")
    public @ResponseBody String uploadBlog(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        String uploadUrl="";
        try {
            if (file!=null) {
                if (!"".equals(filename.trim())) {
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                    file.transferTo(newFile);
                    // 上传到OSS
                    uploadUrl = aliService.upLoad(newFile);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return uploadUrl;
    }



        }
