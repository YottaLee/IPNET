package com.ipnet.entity.contract;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermitContract {
    private String patent_name;
    private String patent_id;
    private String partyA_repre;
    private String partyB_repre;
    private String sign_address;
    private Date sign_date;
    private Date deadline;

}
