package com.ipnet.dao;

import com.ipnet.entity.CompanyUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyDao extends JpaRepository<CompanyUser,String> {
}
