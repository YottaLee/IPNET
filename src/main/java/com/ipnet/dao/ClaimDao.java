package com.ipnet.dao;


import com.ipnet.entity.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClaimDao extends JpaRepository<Claim,String> {

    @Query("select c from Claim c where c.evaluate_id=?1 or c.person=?1 or c.insurance_id=?1")
    ArrayList<Claim> getList(String id);
}
