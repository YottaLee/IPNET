package com.ipnet.entity.communityentity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

/*
* 描述：与用户相关的帖子及用户
* 内容：用户发布，收藏，关注的帖子或用户被关注……等帖子的ID，时间……*/
@Entity
@Data
@AllArgsConstructor
public class Mine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long fid;//存储ID，falseID

    private String tid;//用户相关的用户或者帖子的ID，trueID

    private String time;//关注、收藏或发布的时间

    @Enumerated(value = EnumType.ORDINAL)
    private MineTag tag;//用户区分关注的收藏的发布的……
}
