package com.ipnet.entity;

import com.ipnet.enums.Identity;
import com.ipnet.enums.Industry;
import com.ipnet.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personalUser")
public class PersonalUser {

    @Id
    private String id;//默认为注册时的手机号码或是邮箱

    //若用户使用邮箱注册，需要激活状态和激活码
    private boolean isActive;//用户的激活状态
    private String activeCode;//用户的激活码

    private boolean verified;//用户是否已经实名认证且通过

    private String idPhoto;//用户身份证照片，用于实名认证
    private String name;//用户的姓名
    private String username;//用户的昵称？username？暂时无用
    private String password;//用户的登录密码

    private String image;//用户头像的url
    private String pay_code;

    @ElementCollection(targetClass = Identity.class)
    private List<Identity> identities;//用户的身份认证，可以是专利持有者，购买者，运营者
    private Sex sex;//用户的性别
    private int age;//用户的年龄
    private String telephone;//用户的手机号码，注册时使用
    private String vertification;
    private String email;//用户的邮箱
    private String description;//用户的自我描述
    private Date registerTime;//用户的注册时间
    private String company;//用户所在企业
    private Industry industry;//用户从事的行业
    private String region;//用户所在地区
    @ElementCollection(targetClass = String.class)
    private List<String> bankAccount;//用户的银行账号，可以绑定多张银行卡
    private int credits;//用户拥有的积分
    private double RMB;//用户平台上的人民币或点券（钱包？）


}
