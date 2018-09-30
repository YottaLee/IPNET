package com.ipnet.vo.financevo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class CreateInsuranceVO {
    private String loan_id;
    private String insurance_url;
    private String insurance_comp_name;

    public CreateInsuranceVO(){}

}
