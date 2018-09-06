package com.ipnet.vo.uservo;

import com.ipnet.enums.Industry;
import com.ipnet.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用户实名身份认证，完成认证信息
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonVerify {

    private String id;//默认为注册时的手机号码或是邮箱
    private String idPhoto;//用户身份证照片，用于实名认证
    private String name;//用户的姓名
    private Sex sex;//用户的性别
    private String telephone;//用户的手机号码，注册时使用
    private String description;//用户的自我描述
    private String company;//用户所在企业
    private Industry industry;//用户从事的行业
    private String region;//用户所在地区
}

