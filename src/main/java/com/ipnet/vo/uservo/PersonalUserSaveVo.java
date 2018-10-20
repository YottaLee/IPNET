package com.ipnet.vo.uservo;

import com.ipnet.enums.Industry;
import com.ipnet.enums.Region;
import com.ipnet.enums.Role;
import com.ipnet.enums.Sex;
import lombok.Data;

import javax.print.DocFlavor;

@Data
public class PersonalUserSaveVo {

    private String id;

    private String username;

    private String name;

    private Sex gender;

    private String phone;

    private Industry profession;

    private String company;

    private String region;

    private String statement;

    private String IDcard_img;

    public PersonalUserSaveVo(String username, String name, Sex gender, String phone, Industry profession, String company, String region, String statement, String IDcard_img) {
        this.username = username;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.profession = profession;
        this.company = company;
        this.region = region;
        this.statement = statement;
        this.IDcard_img = IDcard_img;
    }

    public PersonalUserSaveVo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Industry getProfession() {
        return profession;
    }

    public void setProfession(Industry profession) {
        this.profession = profession;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getIDcard_img() {
        return IDcard_img;
    }

    public void setIDcard_img(String IDcard_img) {
        this.IDcard_img = IDcard_img;
    }
}
