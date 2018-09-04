package com.ipnet.dao;

import com.ipnet.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<Company,String> {
}
