package com.ipnet.blservice;

import com.ipnet.utility.IDNotExistsException;

import java.util.List;

public interface AccountBLService {
    boolean MoneyMove(String srcAccount , String destAccount , double amount ,String paytype , String patentId) throws IDNotExistsException;
    void addAccount(String accountId , double amount , String userId);
    /**
     * 最近六个月的人民币总和，按日期排序
     * @return
     */
    List<Integer> getRMBSum();

    /**
     * 最近六个月的ip豆总和，按日期排序
     * @return
     */
    List<Integer> getIPPointsSum();

    /**
     * 最近六个月的利润总和，按日期排序
     * @return
     */
    List<Double> getProfitSum();


}
