package com.ipnet.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class BreakupContract extends Contract{
    @Id
    private String contract_id;
    private int money3;
    private String time1;
    private int money4;
    private String time2;
    private int money5;
    //14
    private int money1;
    private int money2;

    //15
    private String person1;
    private String person2;
    private ArrayList<String> responsibilities;

    //16
    private ArrayList<String> reasons;

    //17
    private String str1;
    private String str2;

    //18
    private int day1;





}
