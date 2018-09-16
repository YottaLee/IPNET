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
public class PermitContract {
    @Id
    private String contract_id;
    private String patent_name;
    private String patent_id;
    private String partyA_repre;
    private String partyB_repre;
    private String sign_address;
    private Date sign_date;
    private Date deadline;

}
