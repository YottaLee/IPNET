package com.ipnet.vo.uservo;

import com.ipnet.enums.CompanyType;
import com.ipnet.enums.Identity;
import com.ipnet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyVerify {

    private String id;//注册时的邮箱

    private String representative;//企业法人姓名
    private String creditCode;//法人信用代码
    private String personPhoto;//企业法人身份证正面照
    private String tel;//联系人/负责人手机号

    private CompanyType type;//企业类型
    private Date establishDate;//成立日期
    private String address;//企业地址
    private double fund;//注册资本
    private String name;//企业名称
    private String busTerm;//营业期限
    private String statement;//简介
    private String field;//业务范围
    private String businessNum;//工商注册号
    private String licence;//营业执照的url
    private String email;//电子邮箱
    private String website;//企业官网


}
