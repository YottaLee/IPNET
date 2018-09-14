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
    private String claimID; //理赔号
    private String loanID; //贷款号
    private String url;//理赔申请文件url
    private Patent_loan_state loan_state;//专利贷款状态
    private String person; //银行名称 默认唯一

    private String evaluate_id;
    private String insurance_id;
    private double government_money;
    private double evaluate_moey;
    private double insurance_money;
}
