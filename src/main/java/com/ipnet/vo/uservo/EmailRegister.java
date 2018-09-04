package com.ipnet.vo.uservo;

import com.ipnet.enums.Role;
import lombok.Data;

@Data
public class EmailRegister {

    private String username;//邮箱

    private String password;//密码

    private Role role;//注册用户的身份，平台企业用户||金融机构||评估机构||保险机构
}
