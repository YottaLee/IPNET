package com.ipnet.dao;

import com.ipnet.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import javax.persistence.Table;

@Repository
@Table(name = "company")
public interface CompanyDao extends JpaRepository<CompanyUser,String> {

}
