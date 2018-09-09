package com.ipnet.dao;

import com.ipnet.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "log")
public interface LogDao extends JpaRepository<Log,Long> {
}
