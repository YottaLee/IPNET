package com.ipnet.dao.contractdao;

import com.ipnet.entity.contract.AgentContract;
import com.ipnet.entity.contract.LoanContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "loan_contract")
public interface LoanContractDao  extends JpaRepository<LoanContract, String> {
}
