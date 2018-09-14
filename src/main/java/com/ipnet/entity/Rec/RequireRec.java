package com.ipnet.entity.Rec;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "require_rec")
public class RequireRec {

    @Id
    private String rec_id;

    private String buyer_id;
    private String require_name;
    private Date day;
    private int points;
    private String detail;
}
