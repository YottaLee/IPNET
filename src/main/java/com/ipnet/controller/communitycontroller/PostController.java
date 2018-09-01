package com.ipnet.controller.communitycontroller;

import com.ipnet.bl.ali.AliServiceImpl;
import com.ipnet.blservice.communityservice.PostBLService;
import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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



    @RequestMapping(value="/test")
    public @ResponseBody void test() throws IOException {

        URL url = new URL("https://ipnet10.oss-cn-beijing.aliyuncs.com/%E6%AF%9B%E6%A6%82%E6%95%B4%E7%90%86.docx");
        InputStream ism=url.openStream();
        byte[] bytes=new byte[2048];
        ism.read(bytes);
        String str=new String(bytes,"utf-8");
        System.err.println(str);
        while(ism.read(bytes)>-1){
            System.err.println(str);
        }

    }

//    @RequestMapping(value = "/read")
//    public @ResponseBody void testtesttest() throws Exception {
//        File remoteFile=new File("https://ipnet10.oss-cn-beijing.aliyuncs.com/%E7%A4%BE%E4%BC%9A%E5%AE%9E%E8%B7%B5%E8%A6%81%E6%B1%82.doc");
//        BufferedReader br=new BufferedReader(new FileReader(remoteFile),"")

    //}


//    @RequestMapping(value = "/testtest")
//    public @ResponseBody
//    void testtest() throws IOException {
//        File file=new File("E:\\test.txt");
//        FileInputStream input = new FileInputStream(file);
//        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain",input);
//        postBLService.uploadFile("test",multipartFile);
//    }

    @RequestMapping(value = "/changeBaseToUrl")
    public @ResponseBody String uploadPicture(@RequestParam String base64, @RequestParam String filename, @RequestParam String projectID){
        return aliService.uploadPicture(projectID,filename+".jpg",base64);
    }

    @RequestMapping("/toUpLoadFile")
    public String toUpLoadFile(){
        return "test";
    }




    @RequestMapping(value = "/createPostID")
    public @ResponseBody String createPostID(String author){
        return postBLService.createID(author);
    }

    @RequestMapping(value = "/publishArticle")
    public @ResponseBody
    ResultMessage publishArticle(PublishArticleVO publishArticleVO) throws IOException {
        return postBLService.publishArticle(publishArticleVO.getPost_id(),publishArticleVO.getAuthor(),publishArticleVO.getPost_name(),publishArticleVO.getPost_tag(),publishArticleVO.getBrief_intro(),publishArticleVO.getContent());
    }

    @RequestMapping(value = "/editArticle")
    public @ResponseBody
    ResultMessage editArticle(EditArticleVO editArticleVO){
        return postBLService.edit(editArticleVO.getPost_id(),editArticleVO.getPost_name(),editArticleVO.getPost_tag(),editArticleVO.getContent());
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

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public ArrayList<RecordVO> recoomend(String author) {
        return postBLService.recommend(author);
    }



        }
