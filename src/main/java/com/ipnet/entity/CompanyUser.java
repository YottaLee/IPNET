package com.ipnet.entity;

import com.ipnet.enums.Company_type;
import com.ipnet.enums.Identity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_user")
public class CompanyUser {
    @Id
    private String tel;//手机号
    private String bus_licence_url;//营业执照的url
    private String company_name;//企业名称
    private Company_type company_type;//企业类型
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
    @ElementCollection(targetClass = String.class)
    private ArrayList<String> bank_accounts;//银行账号
    private String password;//密码
    @ElementCollection(targetClass = Identity.class)
    private ArrayList<Identity> identities;//身份认证
    private String user_name;//用户名
    private String description;//自我描述
    private String register_date;//注册日期
    @ElementCollection(targetClass = String.class)
    private ArrayList<String> patent_id;//拥有专利号
    @ElementCollection(targetClass = String.class)
    private ArrayList<String> patent_pool_id;//拥有专利池号
}
