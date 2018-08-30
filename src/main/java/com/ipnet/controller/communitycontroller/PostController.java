package com.ipnet.controller.communitycontroller;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.EditArticleVO;
import com.ipnet.vo.communityvo.PostVO;
import com.ipnet.vo.communityvo.PublishArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    ResultMessage publishArticle(PublishArticleVO publishArticleVO){
        return postBLService.publishArticle(publishArticleVO.getPost_id(),publishArticleVO.getAuthor(),publishArticleVO.getPost_name(),publishArticleVO.getPost_tag(),publishArticleVO.getBrief_intro(),publishArticleVO.getContent());
    }

    @RequestMapping(value = "/editArticle")
    public @ResponseBody
    ResultMessage editArticle(EditArticleVO editArticleVO){
        return postBLService.edit(editArticleVO.getPost_id(),editArticleVO.getPost_name(),editArticleVO.getPost_tag(),editArticleVO.getContent_url());
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

    @RequestMapping(value = "/getAllArticle")
    public @ResponseBody
    ArrayList<BriefPost> getAllArticle(){
        return postBLService.getAllArticleList();
    }


    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void Download(HttpServletResponse res) {
        String fileName = "1.png";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://"
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String Download() {
        return "/fileDownload";
    }



        }
