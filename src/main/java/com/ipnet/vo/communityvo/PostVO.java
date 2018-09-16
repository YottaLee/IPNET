package com.ipnet.vo.communityvo;

import com.ipnet.entity.communityentity.Post;
import com.ipnet.entity.communityentity.Remark;
import com.ipnet.enums.communityenums.Post_tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class PostVO {
    private String post_id;
    private String author;
    private String post_name;
    private ArrayList<String> post_tag;
    private String content;
    private String publish_time;
    private long visits;
    private long remark_num;
    private long interest_num;//关注数
    private ArrayList<Remark> remark_content;

    public PostVO(){}

    public PostVO(Post post){
        this.post_id=post.getPost_id();
        this.author=post.getAuthor();
        this.post_name=post.getPost_name();
        this.post_tag=new ArrayList<>(post.getPost_tag());
        this.content=post.getContent_url();
        this.publish_time=post.getPublish_time();
        this.visits=post.getVisits();
        this.remark_num=post.getRemark_num();
        this.interest_num=post.getInterest_num();
        this.remark_content=new ArrayList<>(post.getRemark_content());
    }



}
