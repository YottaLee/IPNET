package com.ipnet.vo.financevo;

import com.ipnet.entity.Loan;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode


/**
 * 贷款
 * @author Panxy
 */

public class LoanVO {
    private String loanID; //贷款ID
    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人
    private String bank; //银行名称
    private double money; //金额
    private String time; //期限
    private int evaluation; //评估经济价值结果

    public LoanVO(Loan loan){
        this.loanID=loan.getLoanID();
        this.patentID=loan.getPatentID();
        this.patentID=loan.getPatent();
        this.person=loan.getPerson();
        this.bank=loan.getBank();
        this.money=loan.getActual_money();
        this.time=loan.getActual_time();
        this.evaluation=loan.getEvaluation();
    }

}
