package com.ipnet.entity;

import com.ipnet.vo.financevo.InsuranceVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Table(name="insurance")
@AllArgsConstructor
@NoArgsConstructor
public class Insurance {

    @Id
    private String id;
    private String loan_id;
    private String patent_id;
    private String insurance_url;
    private String person;//借贷人
    private String insurance_id;//保险公司
    private Date date;
    private double money;
    private boolean ifPass;//是否同意保险
    private String url;//投保申请的url
    private String evaluationCompany;//评估机构名称
    private String bank;

    /**
     * @Author: Jane
     * @Description: 该方法仅为生成保险时用
     * @Date: 2018/9/29 10:34
     */
    public Insurance(InsuranceVO insuranceVO){
        SimpleDateFormat df=new SimpleDateFormat("yyyyMMdd-HHmmss");
        String id=df.format(new Date())+"-"+person;
        this.id=id;
        this.loan_id=insuranceVO.getLoanID();
        this.patent_id=insuranceVO.getPatentID();
        this.insurance_url="";
        this.person=insuranceVO.getPerson();
        this.insurance_id=insuranceVO.getInsuranceCompany();
        this.date=new Date();
        this.money=insuranceVO.getMoney();
        this.ifPass=insuranceVO.isPass();
        this.url=insuranceVO.getUrl();
        this.evaluationCompany=insuranceVO.getEvaluationCompany();
        this.bank=insuranceVO.getBank();

    }
}
