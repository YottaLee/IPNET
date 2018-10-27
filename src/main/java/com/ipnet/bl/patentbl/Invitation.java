package com.ipnet.bl.patentbl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author lzb
 * @date 2018/9/16 10:49
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invitation")
public class Invitation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long simpleId;
    private String patentId;
    private String patentPoolId;
    private Date date;
}
