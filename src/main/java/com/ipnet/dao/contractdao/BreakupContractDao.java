package com.ipnet.dao.contractdao;

import com.ipnet.entity.contract.AgentContract;
import com.ipnet.entity.contract.BreakupContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "breakup_contract")
public interface BreakupContractDao  extends JpaRepository<BreakupContract, String> {
}
