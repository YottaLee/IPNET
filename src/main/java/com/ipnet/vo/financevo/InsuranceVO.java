package com.ipnet.vo.financevo;

import com.ipnet.enums.Patent_loan_state;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class InsuranceVO {
    private String loanID; //贷款ID，也就是保险ID
    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人
    private String userId;//专利持有人ID
    private String url;//投保申请的url
    private String bank; //银行名称
    private String insuranceCompany;//保险公司名称
    private String evaluationCompany;//评估机构名称
    private double money; //保费
}
