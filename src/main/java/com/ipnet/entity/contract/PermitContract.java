package com.ipnet.entity.contract;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PermitContract {
    private String patent_name;
    @Id
    private String patent_id;
    private String partyA_repre;
    private String partyB_repre;
    private String sign_address;
    private Date sign_date;
    private Date deadline;

}
