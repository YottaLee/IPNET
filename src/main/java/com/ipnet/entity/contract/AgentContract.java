package com.ipnet.entity.contract;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AgentContract extends Contract{
    @Id
    private String contract_id;
    private String transferor;//转让方
    private String transferor_repre;//法定代表人
    private String acquiring_party;//受让方
    private String acquiring_party_repre;//法定代表人

    //1
    private String pro_name;//项目名称
    private String patent_applicant;//专利申请人
    private String patent_owner;//专利权人
    private Date date_of_application;
    private String id_of_application;
    private String id_of_patent;
    private Date start_date;
    private Date end_date;
    private String protect_range;

    //2
    private String Authorizing_nature;

    //3
    private Date Authorizing_starter;
    private Date Authorizing_end;

    //4.1
    private int percent_one;
    private int percent_two;
    private int percent_three;

    //4.2
    private int day1;
    private String technical_data;

    //4.3
    private String technical_instruction;

    //5.1
    private int use_fee;
    private String payment_by_installment;
    private int day2;
    private int money1;
    private int year;
    private int percent;
    private Date pay_day;

    //5.2
    private String notes;

    //6
    private String duties;

    //10.1
    private int penalty1;

    //10.2
    private int penalty2;

    //10.3
    private int penalty3;

    //10.4
    private int penalty4;

    //11.1
    private int penalty5;

    //11.2
    private int penalty6;


}
