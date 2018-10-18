package com.ipnet.dao;

import com.ipnet.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;


@Repository
@Table(name = "company_user")
public interface CompanyUserDao extends JpaRepository<CompanyUser,String> {

    @Query(value = "select c from CompanyUser c where c.id=:cid")
    CompanyUser findCompanyUserById(@Param("cid") String cid);

    @Query(value = "select c from CompanyUser c where c.role=2 ")
    ArrayList<CompanyUser> getEvaluators();

    @Query("select count(c) from CompanyUser c where c.registerTime<=:t and c.role=0")
    int countUser(@Param("t")Date date);

    @Query("select count(c) from CompanyUser c where c.registerTime<=:t")
    int countAll(@Param("t")Date date);

    @Query(value = "select c from CompanyUser c where c.name=:name")
    CompanyUser findCompanyUserByName(@Param("name") String name);


}
