package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Data
@Table(name="transcation")
@NoArgsConstructor
@AllArgsConstructor
public class Transcation {
    @Id
    private String transcation_id;//交易订单id
    private String buyer;//买方nagid
    private String seller;//卖方id
    private String buyer_bank_account;//买方银行账户号
    private String seller_bank_account;//卖方银行账户号
    private Date transcation_time;//交易时间
    private String patent_id;//交易专利号
}
