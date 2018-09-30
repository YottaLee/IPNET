package com.ipnet.entity;

import com.ipnet.blservice.UserBLService;
import com.ipnet.dao.CompanyUserDao;
import com.ipnet.dao.LoanDao;
import com.ipnet.enums.IfPass;
import com.ipnet.vo.financevo.CreateInsuranceVO;
import com.ipnet.vo.financevo.Evaluator;
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
    private String insurance_id;//保险公司名字
    private Date date;
    private double money;
    private IfPass ifPass;//是否同意保险
    private String evaluationCompany;//评估机构id
    private String bank;

}
