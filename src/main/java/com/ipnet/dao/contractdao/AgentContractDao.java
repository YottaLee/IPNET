package com.ipnet.dao.contractdao;

import com.ipnet.entity.contract.Account;
import com.ipnet.entity.contract.AgentContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "agent_contract")
public interface AgentContractDao   extends JpaRepository<AgentContract, String> {
}
