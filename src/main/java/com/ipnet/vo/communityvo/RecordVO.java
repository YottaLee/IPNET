package com.ipnet.vo.communityvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordVO {

    private String postid;//浏览的帖子ID
    private String postname;//浏览的帖子标题
    private String url;//浏览过的帖子的发帖人
}
