package com.ipnet.datav.area;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lzb
 * @date 2018/11/1 0:03
 */
@Entity
@Data
@ToString
public class Area {

    @Id
    private String area_id;
    private String name;
    private double lng;
    private double lat;
    private double value1;
    private double value2;
    private String info;

}
