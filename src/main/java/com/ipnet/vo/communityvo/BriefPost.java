package com.ipnet.vo.communityvo;

import com.ipnet.entity.communityentity.Post;
import com.ipnet.enums.communityenums.Post_tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
public class BriefPost {
    private String post_id;
    private String author;
    private String post_name;
    private ArrayList<String> post_tag;
    private String brief_intro;
    private String publish_time;
    private long visits;
    private long remark_num;

    public BriefPost(){}

    public BriefPost(Post post){
        this.post_id=post.getPost_id();
        this.author=post.getAuthor();
        this.post_name=post.getPost_name();
        this.post_tag=new ArrayList<>(post.getPost_tag());
        this.brief_intro=post.getBrief_intro();
        this.publish_time=post.getPublish_time();
        this.visits=post.getVisits();
        this.remark_num=post.getRemark_num();
    }
}
