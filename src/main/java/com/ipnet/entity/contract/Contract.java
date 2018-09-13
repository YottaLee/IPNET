package com.ipnet.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    private String contract_id;
    private String partyA;
    private String partyB;
    private String addressA;
    private String addressB;
    private String sign1_url;
    private String sign2_url;
    private String seal1_url;
    private String seal2_url;
    private Date date1;
    private Date date2;
}
