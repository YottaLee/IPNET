package com.ipnet.entity;

import com.ipnet.enums.CompanyType;
import com.ipnet.enums.Identity;
import com.ipnet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class CompanyUser {
    @Id
    private String id;//注册时的邮箱
    private String tel;//手机号

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
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private ArrayList<String> bank_accounts;//银行账号
    private String password;//密码
    //@ElementCollection(targetClass = String.class,fetch = FetchType.LAZY)
    private ArrayList<Identity> identities;//身份认证

    private String description;//自我描述
    private String registerTime;//注册日期

    private boolean isActive;//用户的激活状态
    private String activeCode;//用户的激活码

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLicence() {
        return licence;
    }

    public void setLicence(String licence) {
        this.licence = licence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRepresentative() {
        return representative;
    }

    public void setRepresentative(String representative) {
        this.representative = representative;
    }

    public String getPersonPhoto() {
        return personPhoto;
    }

    public void setPersonPhoto(String personPhoto) {
        this.personPhoto = personPhoto;
    }

    public double getFund() {
        return fund;
    }

    public void setFund(double fund) {
        this.fund = fund;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getBusTerm() {
        return busTerm;
    }

    public void setBusTerm(String busTerm) {
        this.busTerm = busTerm;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public CompanyType getType() {
        return type;
    }

    public void setType(CompanyType type) {
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ArrayList<String> getBank_accounts() {
        return bank_accounts;
    }

    public void setBank_accounts(ArrayList<String> bank_accounts) {
        this.bank_accounts = bank_accounts;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Identity> getIdentities() {
        return identities;
    }

    public void setIdentities(ArrayList<Identity> identities) {
        this.identities = identities;
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
    }*/
}
