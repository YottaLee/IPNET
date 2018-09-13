package com.ipnet.dao;

import com.ipnet.vo.financevo.InsuranceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="insurance")
public interface InsuranceDao extends JpaRepository<InsuranceVO, String> {
}
