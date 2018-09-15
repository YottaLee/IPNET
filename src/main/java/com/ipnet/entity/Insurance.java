package com.ipnet.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private int money;
    private boolean ifPass;//是否同意保险
    private String url;//投保申请的url
    private String evaluationCompany;//评估机构名称
    private String bank;
}
