package com.ipnet.blservice;

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
     *
     * @return
     */
    double getIPIIndex();

    /**
     * 平台的RMB总和的变化接口（暂定最近6个月，即6个数值）
     * @return
     */
    List<Integer> getRMBSum();

    List<Integer> getIPPointsSum();

    List<Double> getProfitSum();

    List<Integer> getMemberSum();

    List<Integer> getUserSum();



}
