package com.ipnet.enums;

public enum Patent_loan_state {
    to_be_loan_application,//申请贷款中
    to_be_checked_by_bank,//待被银行审核
    to_be_value,//申请评估中
    to_be_pay_value,//待支付评估费用
    to_be_evaluation,//待评估中
    to_be_mid_confirm,//被银行中期审核
    to_be_choose_insurance,//待选择保险公司
    to_be_checked_by_insurance,//待被保险公司审核
    to_be_contract_by_insurance,//待保险签约中
    to_be_buy_insurance,//待质押人给银行买保险
    to_be_final_confirm, //待银行最终确认
    to_be_contract_by_loan, //待贷款签约中
    loaning,//质押中
    overdue,//逾期
    to_be_compensation,//待赔付
    free//已完结

}
