package com.ipnet.blservice;

import com.ipnet.bl.AdminBL.IPIFactor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * @date 2018/9/13 16:32
 */
@Service
public interface AdminBLService {

    /**
     * 设置平台IPI指数的5个参数/指标的权重的接口（平台的IPI）
     * @param RMB 人民币的权重值
     * @param IPPoints IP豆的权重值
     * @param profit 收益的权重值
     * @param memberNum IP成员数的权重值
     * @param userNum IPNET用户数的权重值
     *
     * @return IPI
     */
    double setIPIIndexWeigh(int RMB , int IPPoints , int profit , int memberNum , int userNum);


    /**
     * 返回各个因子所占比例
     * @return
     */
    IPIFactor getFactorRatio();

    /**
     *
     * @return IPI指数计算结果
     */
    double getIPIIndex();

    /**
     * 平台的RMB总和的变化接口（暂定最近6个月，即6个数值）
     * @return
     */
    List<Integer> getRMBSum();

    /**
     * 最近六个月的IP豆总和
     * @return
     */
    List<Integer> getIPPointsSum();

    /**
     * 最近六个月的利润总和
     * @return
     */
    List<Double> getProfitSum();

    /**
     * 最近六个月的IP成员数量总和
     * @return
     */
    List<Integer> getMemberSum();

    /**
     * 最近六个月的平台总用户数量总和
     * @return
     */
    List<Integer> getUserSum();



}
