package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "evaluation")
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {

    //评估事件的ID，提交评估申请时自动生成
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //评估申请时间
    private String time;
    //评估申请人
    private String person;
    //评估专利号
    private String patentID;
    //专利持有人上交的说明材料的url
    private String specification;

    //此次评估经手人
    private String agent;
    //评估结果
    private String result;
    //评估结果经济价值
    private double evaluation;
    //评估报告url
    private String report;
    //此次评估费用
    private double money;
    //评估原则
    private String rule;
    //技术价值
    private String tech;

    //评估是否已经结束
    private boolean over;
}
