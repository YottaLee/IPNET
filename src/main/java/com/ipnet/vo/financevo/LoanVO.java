package com.ipnet.vo.financevo;

import com.ipnet.entity.Loan;
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
public class LoanVO {
    private String loanID; //贷款ID
    private String patentID;//专利ID
    private String patent;//专利名称
    private Patent_loan_state loan_state;//专利贷款状态
    private String person; //专利持有人
    private String userId; //用户ID
    private String bank; //银行名称
    private double money; //金额
    private String time; //期限
    private double evaluation; //评估经济价值结果

    public LoanVO(Loan loan){
        this.loanID=loan.getLoanID();
        this.patentID=loan.getPatentID();
        this.patent=loan.getPatent();
        this.bank=loan.getBank();
        this.money=loan.getActual_money();
        this.time=loan.getActual_time();
        this.evaluation=loan.getEvaluation();
        this.loan_state=loan.getState();
        this.person=loan.getPerson();
    }

}
