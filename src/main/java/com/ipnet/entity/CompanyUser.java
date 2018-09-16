package com.ipnet.entity;

import com.ipnet.enums.CompanyType;
import com.ipnet.enums.Identity;
import com.ipnet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companyUser")
public class CompanyUser {
    @Id
    private String id;//注册时的邮箱
    private String tel;//手机号
    private String vertification;

    private boolean verified;//公司是否已经实名认证且通过

    private String image;//公司用户的头像

    private String licence;//营业执照的url
    private String name;//企业名称
    private Role role;//企业角色，评估机构？保险机构？金融机构？普通平台用户？
    private String representative;//企业法人
    private String personPhoto;//企业法人身份证正面照
    private double fund;//注册资本
    private Date establishDate;//成立日期
    private String busTerm;//营业期限
    private String address;//企业地址
    private String email;//电子邮箱
    private String statement;//简介
    private CompanyType type;//企业类型
    private String field;//业务范围
    private String website;//企业官网
    private String businessNum;//工商注册号
    private String creditCode;//法人信用代码
    private double money;//人民币
    private int points;//积分
    @ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private List<String> bank_accounts;//银行账号
    private String pay_code;
    private String password;//密码
    @ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private List<Identity> identities;//身份认证

    private String description;//自我描述
    private Date registerTime;//注册日期

    private boolean isActive;//用户的激活状态
    private String activeCode;//用户的激活码
}
