package com.ipnet.entity.communityentity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "remark")
@AllArgsConstructor
public class Remark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;
    private String post_id;
    private String remark_content;
    private String reviewer;
    private Date remark_time;

    public Remark(){}

    public Remark(String post_id,String remark_content,String reviewer){
        this.post_id=post_id;
        this.remark_content=remark_content;
        this.reviewer=reviewer;
        this.remark_time=new Date();
    }

}
