package com.ipnet.bl.patentbl;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lzb
 * @date 2018/9/16 10:49
 */
@Data
@Entity
@Table(name = "invitation")
public class Invitation {
    @Id
    private String id;
    private String patentId;
    private String patentPoolId;
    private Date date;
}
