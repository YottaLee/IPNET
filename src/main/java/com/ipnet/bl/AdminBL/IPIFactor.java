package com.ipnet.bl.AdminBL;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author lzb
 * @date 2018/9/15 11:00
 */
@Data
public class IPIFactor {

    private double RMBRatio;
    private double IPPointRatio;
    private double profitRatio;
    private double memberRatio;
    private double userRatio;


}
