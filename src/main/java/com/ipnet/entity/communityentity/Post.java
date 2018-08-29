package com.ipnet.entity.communityentity;

import com.ipnet.enums.communityenums.Post_tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "post")
@AllArgsConstructor

public class Post {
    @Id
    private String post_id;
    private String author;
    private String post_name;
    private Post_tag post_tag;
    private String content_url;
    private Date publish_time;
    private long visits;
    private long remark_num;
    private long collect_num;//收藏数
    private long interest_num;//关注数
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Remark> remark_content;

    public Post(){}
    public Post(String author){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String currentTime=df.format(new Date());// new Date()为获取当前系统时间
        this.post_id=currentTime+author;
        this.author=author;
    }

    public Post(String post_id, String author, String post_name, Post_tag post_tag, String content_url){
        this.post_id=post_id;
        this.author=author;
        this.post_name=post_name;
        this.post_tag=post_tag;
        this.content_url=content_url;
    }

}
