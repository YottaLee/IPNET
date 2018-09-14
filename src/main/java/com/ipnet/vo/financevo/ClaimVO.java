package com.ipnet.vo.financevo;

import com.ipnet.entity.Claim;
import com.ipnet.enums.Patent_loan_state;
import lombok.*;

@Data
@AllArgsConstructor
public class ClaimVO {

    private String claimID; //理赔号
    private String loanID; //贷款号
    private String url;//理赔申请文件url
    private Patent_loan_state loan_state;//专利贷款状态
    private String person; //银行名称 默认唯一

    //各个机构实际赔付的金额
    private double government_money;
    private double evaluate_moey;
    private double insurance_money;

    private String evaluate_id;
    private String insurance_id;

    public ClaimVO(){}

    public ClaimVO(Claim claim){
        this.claimID=claim.getClaimID();
        this.loanID=claim.getLoanID();
        this.url=claim.getUrl();
        this.loan_state=claim.getLoan_state();
        this.person=claim.getPerson();
        this.government_money=claim.getGovernment_money();
        this.evaluate_moey=claim.getEvaluate_moey();
        this.insurance_money=claim.getInsurance_money();
        this.insurance_id=claim.getInsurance_id();
        this.evaluate_id=claim.getEvaluate_id();
    }
}
