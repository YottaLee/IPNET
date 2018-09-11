package com.ipnet.enums;

public enum Patent_loan_state {
    free,//已完结
    to_be_value,//申请评估中
    to_be_checked_by_bank,//待被银行审核
    to_be_checked_by_insurance,//待被保险公司审核
    to_be_contract,//待被所有人签约中
    loaning,//质押中
    overdue,//逾期
    to_be_compensation_by_insurance,//待保险公司审核银行理赔申请中
    to_be_compensation_by_evaluation,//待评估机构审核理赔申请
}
