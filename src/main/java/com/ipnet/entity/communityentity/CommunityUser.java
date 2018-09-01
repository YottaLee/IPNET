package com.ipnet.entity.communityentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "com_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUser {

    @Id
    private String userid;//用户的ID，与非社区部分的ID保持一直，默认为注册时的手机号码或者邮箱
    //用户的昵称，积分，钱包余额……不进行重复存储

    private String nickname;//用户的昵称

    private String tags;//用户的标签，感兴趣的方面？用逗号分隔

    private int releasedpost;//用户自己发布的帖子数
    private int interestedpost;//用户关注的帖子数
    //private int collectedpost;//用户收藏的帖子数

    private int interesteduser;//用户关注的其他用户数
    private int fans;//用户的粉丝数

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Mine> mines;//与用户相关的帖子或用户的列表

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Record> history;//用户的浏览记录

}
