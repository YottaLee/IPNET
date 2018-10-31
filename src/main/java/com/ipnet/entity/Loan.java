package com.ipnet.entity;

import com.ipnet.enums.Patent_loan_state;
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
    private String userID; //专利持有人ID
    private String person;
    private String transactionId; //交易ID

    private String way; //贷款用途
    private String time;//质押申请时间
    private double evaluation; //评估经济价值结果
    private double expect_money; //预期金额
    private String expect_time; //预期质押期限

    private Patent_loan_state state;

    private String bankId;
    private String bank; //银行名称
    private String application;//向银行提出质押申请的申请表
    private double accept_money;//银行接受金额
    private String accept_time;//银行接受期限

    private double return_money; //还款金额
    private double compensation; //赔偿金额

    private String insuranceId;
    private String insurance;//保险公司
    private String policy;//保险保单

    private double actual_money;//成交金额
    private String actual_time;//成交期限
    private double insurance_expect_money;
    private String insurance_expect_time;

    private String contractid;//质押保险合同ID

    private boolean ownerPass;//专利持有者同意

    private boolean bankPass;//银行方同意
    private boolean ifInsurance;//是否同意让专利持有人购买专利质押贷款保证保险
    private String banksign;//银行方面同意签字照url

    private boolean insurancePass;//保险公司方同意
    private String insurancesign;//保险机构同意签字照url

    private boolean evaluationPass;//评估机构方同意
    private String evaluationsign;//评估机构同意签字照url

    private String iposign;//知识产权局的盖章照url
    private String financesign;//财政局盖章url





}
