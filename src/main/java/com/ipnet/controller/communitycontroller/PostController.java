package com.ipnet.controller.communitycontroller;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;
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

    @Autowired
    private PostBLService postBLService;

    @RequestMapping(value = "/changeBaseToUrl")
    public @ResponseBody String uploadPicture(@RequestParam String base64, @RequestParam String filename, @RequestParam String projectID){
        return aliService.uploadPicture(projectID,filename+".jpg",base64);
    }

    @RequestMapping("/toUpLoadFile")
    public String toUpLoadFile(){
        return "test";
    }


    @RequestMapping(value = "/uploadFile")
    public @ResponseBody String uploadBlog(MultipartFile file) {
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

    @RequestMapping(value = "/createPostID")
    public @ResponseBody String createPostID(String author){
        return postBLService.createID(author);
    }

    @RequestMapping(value = "/publishArticle")
    public @ResponseBody
    ResultMessage publishArticle(String post_id, String author, String post_name, ArrayList<Post_tag> post_tag, String content_url){
        return postBLService.publishArticle(post_id,author,post_name,post_tag,content_url);
    }

    @RequestMapping(value = "/editArticle")
    public @ResponseBody
    ResultMessage editArticle(String post_id, String post_name, ArrayList<Post_tag> post_tag, String content_url){
        return postBLService.edit(post_id,post_name,post_tag,content_url);
    }

    @RequestMapping(value = "/deleteArticle")
    public @ResponseBody
    ResultMessage deleteArticle(String post_id){
        return postBLService.deleteArticle(post_id);
    }

    @RequestMapping(value = "/remark")
    public @ResponseBody
    ResultMessage remark(String post_id, String reviewer, String remark_content){
        return postBLService.remark(post_id,reviewer,remark_content);
    }

    @RequestMapping(value = "/readArticle")
    public @ResponseBody
    PostVO readArticle(String post_id,String reader){
        return postBLService.readArticle(post_id,reader);
    }

    @RequestMapping(value = "/readArticleList")
    public @ResponseBody
    ArrayList<BriefPost> readArticleList(String author){
        return postBLService.readArticleList(author);
    }

    @RequestMapping(value = "/searchArticle")
    public @ResponseBody
    ArrayList<BriefPost> searchArticle(String keywords){
        return postBLService.searchArticle(keywords);
    }





        }
