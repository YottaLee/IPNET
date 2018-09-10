package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "loan")
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    private String loanID;
    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人
    private int evaluation; //评估经济价值结果
    private double expect_money; //预期金额
    private String expect_time; //预期质押期限

    private String bank; //银行名称
    private boolean pass;//银行是否同意质押申请，即质押双方是否已经签署了合同
    private String loanContract;//质押合同ID
    private double actual_money;//成交金额
    private String actual_time;//成交期限
    private double insurance_expect_money;
    private String insurance_expect_time;

    private String insurance;//保险公司
    private boolean accept;//保险公司是否同意担保,即保险双方是否已经签署了合同
    private boolean toClaim;//保险公司是否同意理赔
    private String insuranceContract;//保险合同ID



}
