package com.ipnet.datav.data_item;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lzb
 * @date 2018/11/1 1:01
 */
@Entity
@Data
@ToString
public class DataItem {

    @Id
    private String id;
    private int value;
//    private String prefix;
//    private String suffix;

}
