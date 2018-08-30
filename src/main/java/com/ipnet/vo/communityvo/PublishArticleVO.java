package com.ipnet.vo.communityvo;

import com.ipnet.enums.communityenums.Post_tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PublishArticleVO {
    private String post_id;
    private String author;
    private String post_name;
    private ArrayList<Post_tag> post_tag;
    private  String brief_intro;
    private String content;

}
