package com.ipnet.entity.Rec;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "patent_or_manager_rec")
@NoArgsConstructor
@AllArgsConstructor
public class PatentOrManagerRec {

    @Id
    private String rec_id;

    private String id;
    private Date day;
    private int points;

    private int type;//0代表专利广告 1代表代理方广告
}
