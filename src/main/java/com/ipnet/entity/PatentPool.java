package com.ipnet.entity;

import com.ipnet.enums.Industry;
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
@Table(name = "pool")
public class PatentPool {
    @Id
    private String id;//专利池的ID，作为唯一标识，由系统自动生成

    private String name;//专利池的名字
    private String owner;//专利池的创建者ID
    private String picture;//专利池的头像？的url
    private Industry industry;//池内专利所属行业
    private String description;//专利池简介
    private String createTime;//专利池的创建时间

    //新添加
    private int amount; //池子容量
    private String profile; //专利池简介

    @ElementCollection(targetClass = String.class)
    private List<String> managers;//专利池的管理团队

    @ElementCollection(targetClass = String.class)
    private List<String> users;//专利池内的专利持有者列表

    @ElementCollection(targetClass = String.class)
    private List<String> patents;//专利池内的专利列表

    //新添加
    @ElementCollection(targetClass = String.class)
    private List<String> applypatents;      //申请入池专利列表

    public void addToapplyPatents(String ipId){
        this.applypatents.add(ipId);
    }

    public void denyApply(String ipId){
        if (this.applypatents == null || this.applypatents.size()==0){
            return;
        }
        this.applypatents.remove(ipId);
    }
    public void addPatent(String ipId){
        this.patents.add(ipId);
    }
    public  void acceptApply(String ipId){
        denyApply(ipId);
        this.patents.add(ipId);
    }
     public boolean isFull(){
        return (this.patents.size() > this.amount);
     }
}
