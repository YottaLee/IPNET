package com.ipnet.dao;

import com.ipnet.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface InsuranceDao extends JpaRepository<Insurance,String>{

    @Query("select i from Insurance i where i.insurance_id=?1")
    ArrayList<Insurance> getInsuranceList(String insurance_id);
}
