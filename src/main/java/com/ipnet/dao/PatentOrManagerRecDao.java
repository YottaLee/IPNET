package com.ipnet.dao;

import com.ipnet.entity.Rec.PatentOrManagerRec;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatentOrManagerRecDao extends JpaRepository<PatentOrManagerRec,String> {
}
