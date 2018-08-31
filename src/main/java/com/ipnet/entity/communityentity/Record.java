package com.ipnet.entity.communityentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {//用户的浏览记录
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String postid;
    private String postname;
    private Date time;
}
