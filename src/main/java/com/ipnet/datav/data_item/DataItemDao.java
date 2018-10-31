package com.ipnet.datav.data_item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lzb
 * @date 2018/11/1 1:05
 */
@Repository
public interface DataItemDao extends JpaRepository<DataItem,String>{
}
