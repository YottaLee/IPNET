package com.ipnet.bl.AdminBL;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IPI 指数中各个权重占比
 * @author lzb
 * @date 2018/9/13 16:42
 */
@Data
@Entity
@Table(name = "IPIFactor")
public class IPIFactorWeigh {

    @Id
    private String id;
    private double RMBRatio;
    private double IPPointRatio;
    private double profitRatio;
    private double memberRatio;
    private double userRatio;


}
