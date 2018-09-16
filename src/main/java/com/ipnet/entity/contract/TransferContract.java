package com.ipnet.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TransferContract extends Contract {
    @Id
    private String contract_id;
    private Date time;
    private String address;

    //3
    private String name;

    //4
    private String type;
    private String patent_owner;
    private String patent_designer;
    private Date assign_time;
    private String patent_id;
    private Date start_time;
    private Date end_time;
    private String year;

    //6.1
    private Date sixth_start_time;
    private Date sixth_end_time;
    private String sixth_range;
    private String sixth_way;

    //6.2
    private Date apply_start_time;
    private Date apply_end_time;
    private String apply_range;
    private String apply_way;

    //8
    private String content;

    //9
    private int pay;
    private String pay_way;

    //10
    private ArrayList<String> numList;
    private String tenth_str1;
    private String money1;
    private String percent;
    private String calculate_way;
    private String others_way;
    private String choose;

    //11
    private String solve_way;
    private String arbitration_commission;

    //13
    private String str;



}
