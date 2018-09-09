package com.ipnet.entity;

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
    private String id;
    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人

    private String bank; //银行名称
    private String insurance;//保险公司
    private boolean accept;//保险公司是否同意担保
    private String insuranceContract;//保险合同ID

    private int money; //金额
    private String time; //期限
    private int evaluation; //评估经济价值结果
}
