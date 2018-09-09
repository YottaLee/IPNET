package com.ipnet.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanContract extends Contract{
    private String party_A;
    private String postal_address_of_A;
    private String legal_repre_of_A;
    private String party_B;
    private String postal_address_of_B;
    private String legal_repre_of_B;
    private String register_date;
    private Date pledge_start_time;
    private Date pledge_end_time;
    private String second_money;
    private Date second_start_time;
    private Date second_end_time;
    private Date fourth_time;
    private String fourth_money;
    private String fifth_money;
    private String tenth_date;
    private int sixteenth_percent;
    private String others;
    private String ninteenth;

}
