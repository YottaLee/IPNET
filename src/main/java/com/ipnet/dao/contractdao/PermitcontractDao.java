package com.ipnet.dao.contractdao;

import com.ipnet.entity.contract.AgentContract;
import com.ipnet.entity.contract.PermitContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "permit_contract")
public interface PermitcontractDao extends JpaRepository<PermitContract , String> {
}
