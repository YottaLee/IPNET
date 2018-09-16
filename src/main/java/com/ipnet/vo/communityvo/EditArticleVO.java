package com.ipnet.vo.communityvo;

import com.ipnet.enums.communityenums.Post_tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditArticleVO {
    private String post_id;
    private String post_name;
    private ArrayList<String> post_tag;
    private String content;
}
