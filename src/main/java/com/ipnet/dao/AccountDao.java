package com.ipnet.dao;

import com.ipnet.entity.contract.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "account")
public interface AccountDao extends JpaRepository<Account, String> {

}
