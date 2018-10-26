package com.ipnet.dao;

import com.ipnet.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;

@Repository
@Table(name="insurance")
public interface InsuranceDao extends JpaRepository<Insurance,String>{

    @Query("select i from Insurance i where i.loan_id=?1")
    Insurance getInsurance(String loan_id);

    @Query("select i from Insurance i where i.insurance_id=?1")
    ArrayList<Insurance> getInsuranceList(String insurance_id);
}
