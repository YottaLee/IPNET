package com.ipnet.utility;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Service;

/**
 * @author lzb
 * @date 2018/7/21 11:31
 */
@Service
public class TransHelper {

    private static Mapper mapper = new DozerBeanMapper();

    public Object transTO(Object src , Class dest){
        return mapper.map(src,dest);
    }

}
