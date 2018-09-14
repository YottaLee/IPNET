package com.ipnet.dao;

import com.ipnet.entity.Claim;
import com.ipnet.vo.financevo.ClaimVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name="insurance")
public interface ClaimDao extends JpaRepository<Claim, String> {
}
