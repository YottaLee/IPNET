package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "log")
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //操作者
    private String username;
    //操作内容
    private String operation;
    //方法名
    private String method;
    //参数
    private String param;
    //ip地址
    private String ip;
    //操作时间
    private Date time;
}
