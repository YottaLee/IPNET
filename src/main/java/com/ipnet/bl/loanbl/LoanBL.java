package com.ipnet.bl.loanbl;

import com.ipnet.blservice.LoanBLService;
import com.ipnet.dao.LoanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanBL implements LoanBLService {

    @Autowired
    private LoanDao loanDao;
}
