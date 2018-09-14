package com.ipnet.entity;

import com.ipnet.enums.Patent_loan_state;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim")
@Data
public class Claim {
    @Id
    private String claimID; //保单号
    private String loanID; //贷款号
    private String url;//理赔申请文件url
    private Patent_loan_state loan_state;//专利贷款状态
    private String person; //投保单位
    private String address; //地址
    private String time; //出险时间
    private String bank; //银行
    private String bankName; //户名
    private String bankID; //银行账号


    private double government_money;
    private double evaluate_moey;
    private double insurance_money;
}
