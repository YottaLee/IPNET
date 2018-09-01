package com.ipnet.vo.communityvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CUserVO {
    private String userid;//用户的ID，与非社区部分的ID保持一直，默认为注册时的手机号码或者邮箱
    //用户的昵称，积分，钱包余额……不进行重复存储
//
//    private double credits;//用户的积分
//    private double wallet;//用户的钱包
    private String url;//用户头像的url

    private String nickname;//用户的昵称

    private String[] myTags;//用户的标签，感兴趣的方面？用逗号分隔

    private int releasedpost;//用户自己发布的帖子数
    private int interestedpost;//用户关注的帖子数
    //private int collectedpost;//用户收藏的帖子数

    private int interesteduser;//用户关注的其他用户数
    private int fans;//用户的粉丝数
}
