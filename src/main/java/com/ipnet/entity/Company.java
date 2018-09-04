package com.ipnet.entity;

import com.ipnet.enums.Identity;
import com.ipnet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company {
    @Id
    private String id;//注册时的邮箱
    private String tel;//手机号

    private String image;//公司用户的头像

    private String bus_licence_url;//营业执照的url
    private String company_name;//企业名称
    private Role role;//企业角色，评估机构？保险机构？金融机构？普通平台用户？
    private String juridical_person;//企业法人
    private double registered_capital;//注册资本
    private Date establish_date;//成立日期
    private String bus_term;//营业期限
    private String bus_address;//企业地址
    private String email;//电子邮箱
    private String brief_intro;//简介
    private String bus_scope;//业务范围
    private String bus_web_url;//企业官网
    private String bus_register_num;//工商注册号
    private String credit_code;//法人信用代码
    private double money;//人民币
    private int points;//积分
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private ArrayList<String> bank_accounts;//银行账号
    private String password;//密码
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private ArrayList<Identity> identities;//身份认证
    private String user_name;//用户名
    private String description;//自我描述
    private String registerTime;//注册日期

    private boolean isActive;//用户的激活状态
    private String activeCode;//用户的激活码
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    //private ArrayList<String> patent_id;//拥有专利号
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    //private ArrayList<String> patent_pool_id;//拥有专利池号
}
