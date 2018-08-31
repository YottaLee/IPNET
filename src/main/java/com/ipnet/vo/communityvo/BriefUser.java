package com.ipnet.vo.communityvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BriefUser {

    private String url;//用户的头像
    private String userid;//用户的ID
    private String nickname;//用户的昵称
    private int releasedpost;//用户发帖数
    private int fans;//用户粉丝数
}
