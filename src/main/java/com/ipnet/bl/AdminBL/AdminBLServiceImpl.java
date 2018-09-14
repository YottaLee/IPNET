package com.ipnet.bl.AdminBL;

import com.ipnet.blservice.AdminBLService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * @date 2018/9/13 16:48
 */
@Service
public class AdminBLServiceImpl implements AdminBLService {

    private IPIFactorWeigh ipiFactorWeigh = new IPIFactorWeigh();

    @Override
    public double setIPIIndexWeigh(int RMB, int IPPoints, int profit, int memberNum, int userNum) {
        return 0;
    }

    @Override
    public double getIPIIndex() {
        return 0;
    }

    @Override
    public List<Integer> getRMBSum() {
        return null;
    }

    @Override
    public List<Integer> getIPPointsSum() {
        return null;
    }

    @Override
    public List<Double> getProfitSum() {
        return null;
    }

    @Override
    public List<Integer> getMemberSum() {
        return null;
    }

    @Override
    public List<Integer> getUserSum() {
        return null;
    }
}
