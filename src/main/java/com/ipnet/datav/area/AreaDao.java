package com.ipnet.datav.area;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2018/11/1 0:09
 */
@Repository
public interface AreaDao extends JpaRepository<Area,String>{
}
