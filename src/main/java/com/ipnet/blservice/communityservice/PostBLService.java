package com.ipnet.blservice.communityservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;
import com.ipnet.vo.communityvo.RecordVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public interface PostBLService {
    String createID(String author);
    ResultMessage publishArticle(String post_id, String author, String post_name, ArrayList<String> post_tag,String brief_intro, String content) throws IOException;
    ResultMessage edit(String post_id, String post_name, ArrayList<String> post_tag, String content_url);
    ResultMessage deleteArticle(String post_id);
    ResultMessage remark(String post_id, String reviewer, String remark_content);
    PostVO readArticle(String post_id,String reader) throws IOException;
    ArrayList<BriefPost> readArticleList(String author);
    ArrayList<BriefPost> getAllArticleList();
    ArrayList<RecordVO> recommend(String author);
    ArrayList<BriefPost> searchArticle(String keywords);
    String downLoadFromUrl(String urlStr) throws IOException;


    ResultMessage addInterestNum(String post_id);
    ResultMessage minusInterestNum(String post_id);
    BriefPost getBriefPostByID(String post_id);


//    void saveAsFile(String content);
    String uploadFile(String post_id,MultipartFile file);
}
