package com.ipnet.vo.uservo;

import com.ipnet.enums.Industry;
import com.ipnet.enums.Region;
import com.ipnet.enums.Sex;

public class UserInfoVo {
    String name;
    Sex gender;
    String phone;
    Industry profession;
    String company;
    String region;
    String statement;
    String IDcard_img;

    public UserInfoVo() {
    }

    public UserInfoVo(String name, Sex gender, String phone, Industry profession, String company, String region, String statement, String IDcard_img) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.profession = profession;
        this.company = company;
        this.region = region;
        this.statement = statement;
        this.IDcard_img = IDcard_img;
    }
}
