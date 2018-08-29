package com.ipnet.blservice.communityservice;

import com.ipnet.enums.ResultMessage;
import com.ipnet.enums.communityenums.Post_tag;
import com.ipnet.vo.communityvo.BriefPost;
import com.ipnet.vo.communityvo.PostVO;

import java.util.ArrayList;


public interface PostBLService {
    String createID(String author);
    ResultMessage publishArticle(String post_id, String author, String post_name, Post_tag post_tag, String content_url);
    ResultMessage edit(String post_id, String post_name, Post_tag post_tag, String content_url);
    ResultMessage remark(String post_id, String reviewer, String remark_content);
    PostVO readArticle(String post_id);
    ArrayList<BriefPost> readArticleList(String author);
}
