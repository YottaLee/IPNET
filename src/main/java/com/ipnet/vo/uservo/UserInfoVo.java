package com.ipnet.vo.uservo;

import com.ipnet.enums.Industry;
import com.ipnet.enums.Region;
import com.ipnet.enums.Sex;

import java.util.Date;

public class UserInfoVo {
    String name;//用户名
    int credit; //积分
    Sex gender;
    String phone;
    Industry profession;
    String company;
    String region;
    String statement;
    String IDcard_img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public int getPatentNum() {
        return patentNum;
    }

    public void setPatentNum(int patentNum) {
        this.patentNum = patentNum;
    }

    public int getPatentPoolNum() {
        return patentPoolNum;
    }

    public void setPatentPoolNum(int patentPoolNum) {
        this.patentPoolNum = patentPoolNum;
    }

    Date registerTime;
    int patentNum;
    int patentPoolNum;

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

    public UserInfoVo(String name,int credit, Sex gender, String phone, Industry profession, String company, String region, String statement, String IDcard_img) {
        this.name = name;
        this.credit = credit;
        this.gender = gender;
        this.phone = phone;
        this.profession = profession;
        this.company = company;
        this.region = region;
        this.statement = statement;
        this.IDcard_img = IDcard_img;
    }
}
