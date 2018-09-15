package com.ipnet.bl.AdminBL;

import com.ipnet.blservice.AccountBLService;
import com.ipnet.blservice.AdminBLService;
import com.ipnet.blservice.UserBLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzb
 * @date 2018/9/13 16:48
 */
@Service
public class AdminBLServiceImpl implements AdminBLService {

    @Value("${IPIRatio.RMBRatio}")
    private double RMBRatio;
    @Value("${IPIRatio.IPPointRatio}")
    private double IPPointRatio;
    @Value("${IPIRatio.profitRatio}")
    private double profitRatio;
    @Value("${IPIRatio.memberRatio}")
    private double memberRatio;
    @Value("${IPIRatio.userRatio}")
    private double userRatio;

    private double IPIIndex;

    @Autowired
    private UserBLService userBLService;

    @Autowired
    private AccountBLService accountBLService;

    @Override
    public double setIPIIndexWeigh(int RMB, int IPPoints, int profit, int memberNum, int userNum) {
        this.RMBRatio = RMB;
        this.IPPointRatio = IPPoints;
        this.profitRatio = profit;
        this.memberRatio = memberNum;
        this.userRatio = userNum;

        return this.getIPIIndex();
    }

    @Override
    public IPIFactor getFactorRatio() {
        IPIFactor factor = new IPIFactor();
        double sum = this.IPPointRatio + this.memberRatio + this.userRatio + this.profitRatio + this.RMBRatio;
        factor.setIPPointRatio(this.IPPointRatio / sum);
        factor.setMemberRatio(this.memberRatio / sum);
        factor.setProfitRatio(this.profitRatio / sum);
        factor.setUserRatio(this.userRatio / sum);
        factor.setRMBRatio(this.RMBRatio / sum);
        return factor;
    }

    @Override
    public double getIPIIndex() {
        double result = this.IPPointRatio * this.getIPPointsSum().get(this.getIPPointsSum().size() - 1)
                + this.RMBRatio * this.getRMBSum().get(this.getRMBSum().size() - 1)
                + this.profitRatio * this.getProfitSum().get(this.getProfitSum().size() - 1)
                + this.userRatio * this.getUserSum().get(this.getUserSum().size() - 1)
                + this.memberRatio * this.getMemberSum().get(this.getMemberSum().size() - 1);
        this.IPIIndex = result;
        return this.IPIIndex;
    }

    @Override
    public List<Integer> getRMBSum() {
        return this.accountBLService.getRMBSum();
    }

    @Override
    public List<Integer> getIPPointsSum() {
        return this.accountBLService.getIPPointsSum();
    }

    @Override
    public List<Double> getProfitSum() {
        return this.accountBLService.getProfitSum();
    }

    @Override
    public List<Integer> getMemberSum() {
        return this.userBLService.getUserSum();
    }

    @Override
    public List<Integer> getUserSum() {
        return this.userBLService.getUserSum();
    }
}
