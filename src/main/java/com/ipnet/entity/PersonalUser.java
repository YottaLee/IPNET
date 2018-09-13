package com.ipnet.entity;

import com.ipnet.enums.Identity;
import com.ipnet.enums.Industry;
import com.ipnet.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String email;//用户的邮箱
    private String description;//用户的自我描述
    private String registerTime;//用户的注册时间
    private String company;//用户所在企业
    private Industry industry;//用户从事的行业
    private String region;//用户所在地区
    @ElementCollection(targetClass = String.class)
    private List<String> bankAccount;//用户的银行账号，可以绑定多张银行卡
    private int credits;//用户拥有的积分
    private double RMB;//用户平台上的人民币或点券（钱包？）

    /*
    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(String idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(List<Identity> identities) {
        this.identities = identities;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Industry getIndustry() {
        return industry;
    }

    public void setIndustry(Industry industry) {
        this.industry = industry;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(List<String> bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public double getRMB() {
        return RMB;
    }

    public void setRMB(double RMB) {
        this.RMB = RMB;
    }*/


}
