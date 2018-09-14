package com.ipnet.enums;

public enum Patent_loan_state {
    free,//已完结 0
    to_be_value,//申请评估中 1
    to_be_pay_value,//待支付评估费用 2
    to_be_evaluation,//待评估中 3
    to_be_loan_application,//申请贷款中 4
    to_be_checked_by_bank,//待被银行审核 5
    to_be_checked_by_insurance,//待被保险公司审核 6
    to_be_contract,//待被所有人签约中 7
    to_be_buy_insurance,//待质押人给银行买保险 8
    to_be_paid_by_bank,//待银行付款给质押人 9
    loaning,//质押中 10
    overdue,//逾期 11
    to_be_compensation//待赔付 12

}
