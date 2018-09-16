package com.ipnet.entity.contract;

import com.ipnet.enums.ContractState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class  Contract {


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
    private ContractState contractType;

    public ContractState getContractType() {
        return contractType;
    }

    public void setContractType(ContractState contractType) {
        this.contractType = contractType;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getAddressA() {
        return addressA;
    }

    public void setAddressA(String addressA) {
        this.addressA = addressA;
    }

    public String getAddressB() {
        return addressB;
    }

    public void setAddressB(String addressB) {
        this.addressB = addressB;
    }

    public String getSign1_url() {
        return sign1_url;
    }

    public void setSign1_url(String sign1_url) {
        this.sign1_url = sign1_url;
    }

    public String getSign2_url() {
        return sign2_url;
    }

    public void setSign2_url(String sign2_url) {
        this.sign2_url = sign2_url;
    }

    public String getSeal1_url() {
        return seal1_url;
    }

    public void setSeal1_url(String seal1_url) {
        this.seal1_url = seal1_url;
    }

    public String getSeal2_url() {
        return seal2_url;
    }

    public void setSeal2_url(String seal2_url) {
        this.seal2_url = seal2_url;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }
}
