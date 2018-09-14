package com.ipnet.bl.AdminBL;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @author lzb
 * @date 2018/9/13 16:49
 */
@Repository
@Table(name = "IPIFactor")
public interface IPIHelper extends JpaRepository<IPIFactorWeigh , String>{



}
