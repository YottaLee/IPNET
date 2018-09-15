package com.ipnet.controller.communitycontroller;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private AliServiceImpl aliService;

    @Autowired
    private PostBLService postBLService;


    @RequestMapping(value = "/testtest")
    public @ResponseBody
    void testtest() throws IOException {
        try {
            postBLService.downLoadFromUrl("https://ipnet10.oss-cn-beijing.aliyuncs.com/20180902001530jane.txt");
        } catch (Exception e) {
        }
    }

    @RequestMapping(value = "/changeBaseToUrl")
    public @ResponseBody
    String uploadPicture(@RequestParam String base64, @RequestParam String filename, @RequestParam String projectID) {
        return aliService.uploadPicture(projectID, filename, base64);
    }

    /**
     * @param file 上传的文件
     * @return
     */

    @RequestMapping("/upLoadFile")
    public @ResponseBody
    String upLoadFile(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return postBLService.uploadFile(file.getName(), file);
    }


    @RequestMapping(value = "/createPostID")
    public @ResponseBody
    String createPostID(@RequestParam String author) {
        return postBLService.createID(author);
    }

    @RequestMapping(value = "/publishArticle")
    public @ResponseBody
    ResultMessage publishArticle(@RequestBody PublishArticleVO publishArticleVO) throws IOException {
        System.err.println(publishArticleVO.getPost_id() + "77777777777");
        return postBLService.publishArticle(publishArticleVO.getPost_id(), publishArticleVO.getAuthor(), publishArticleVO.getPost_name(), publishArticleVO.getPost_tag(), publishArticleVO.getBrief_intro(), publishArticleVO.getContent());
    }

    @RequestMapping(value = "/editArticle")
    public @ResponseBody
    ResultMessage editArticle(@RequestBody EditArticleVO editArticleVO) {
        return postBLService.edit(editArticleVO.getPost_id(), editArticleVO.getPost_name(), editArticleVO.getPost_tag(), editArticleVO.getContent());
    }

    @RequestMapping(value = "/deleteArticle")
    public @ResponseBody
    ResultMessage deleteArticle(@RequestParam String post_id) {
        return postBLService.deleteArticle(post_id);
    }

    @RequestMapping(value = "/remark")
    public @ResponseBody
    ResultMessage remark(@RequestParam String post_id, @RequestParam String reviewer, @RequestParam String remark_content) {
        return postBLService.remark(post_id, reviewer, remark_content);
    }

    @RequestMapping(value = "/readArticle")
    public @ResponseBody
    PostVO readArticle(@RequestParam String post_id, @RequestParam String reader) throws IOException {
        return postBLService.readArticle(post_id, reader);
    }

    @RequestMapping(value = "/readArticleList")
    public @ResponseBody
    ArrayList<BriefPost> readArticleList(@RequestParam String author) {
        return postBLService.readArticleList(author);
    }

    @RequestMapping(value = "/searchArticle")
    public @ResponseBody
    ArrayList<BriefPost> searchArticle(@RequestParam String keywords) {
        return postBLService.searchArticle(keywords);
    }

    @RequestMapping(value = "/getAllArticle")
    public @ResponseBody
    ArrayList<BriefPost> getAllArticle() {
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

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public ArrayList<RecordVO> recoomend(String author) {
        return postBLService.recommend(author);
    }


}
