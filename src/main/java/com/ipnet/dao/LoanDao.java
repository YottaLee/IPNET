package com.ipnet.dao;

import com.ipnet.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "loan")
public interface LoanDao extends JpaRepository<Loan,String> {
}
