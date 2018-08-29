package com.ipnet.blservice.communityservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;

import java.util.ArrayList;
import java.util.List;


public interface PostBLService {
    String createID(String author);
    ResultMessage publishArticle(String post_id, String author, String post_name, ArrayList<Post_tag> post_tag, String content_url);
    ResultMessage edit(String post_id, String post_name, ArrayList<Post_tag> post_tag, String content_url);
    ResultMessage deleteArticle(String post_id);
    ResultMessage remark(String post_id, String reviewer, String remark_content);
    PostVO readArticle(String post_id,String reader);
    ArrayList<BriefPost> readArticleList(String author);

    ArrayList<BriefPost> searchArticle(String keywords);


    ResultMessage addInterestNum(String post_id);
    ResultMessage minusInterestNum(String post_id);
    ResultMessage addRemarkNum(String post_id);
}
