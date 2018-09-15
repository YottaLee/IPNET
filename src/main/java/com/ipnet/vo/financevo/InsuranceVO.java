package com.ipnet.vo.financevo;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.entity.Insurance;
import com.ipnet.enums.Patent_loan_state;
import com.ipnet.utility.IDNotExistsException;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class InsuranceVO {

    @Autowired
    private PatentHelper patentHelper;

    private String id;
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

    public InsuranceVO(Insurance insurance) throws IDNotExistsException {
        this.id=insurance.getId();
        this.loanID=insurance.getLoan_id();
        this.patentID=insurance.getPatent_id();
        this.patent=patentHelper.receivePatentName(insurance.getPatent_id());
        this.person=insurance.getPerson();
        this.url=insurance.getUrl();
        this.bank=insurance.getBank();
        this.insuranceCompany=insurance.getInsurance_id();
        this.evaluationCompany=insurance.getEvaluationCompany();
        this.money=insurance.getMoney();
    }
}
