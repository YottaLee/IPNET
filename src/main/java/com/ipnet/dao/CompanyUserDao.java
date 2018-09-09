package com.ipnet.dao;

import com.ipnet.entity.CompanyUser;
import com.ipnet.entity.PersonalUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;
import java.util.ArrayList;


@Repository
@Table(name = "companyPersonal_user")
public interface CompanyUserDao extends JpaRepository<CompanyUser,String> {

    CompanyUser findCompanyUserById(String id);

    CompanyUser findCompanyUserByName(String name);


}
