package com.ipnet.vo.financevo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class InsuranceVO {


    @Id
    private String insurance_contractid;

    private String loanID; //贷款号
    private String url;//理赔申请文件url
    private String person; //投保单位
    private String address; //地址
    private String time; //出险时间
    private String reason; //出险原因
    private String bank; //银行
    private String bankName; //户名
    private String bankID; //银行账号
    private String insuranceID; //保单号
    private int money; //申请金额
}
