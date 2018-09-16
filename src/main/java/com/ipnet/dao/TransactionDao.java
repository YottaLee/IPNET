package com.ipnet.dao;

import com.ipnet.entity.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "transaction")
public interface TransactionDao extends JpaRepository<Transaction, Long> {

}
