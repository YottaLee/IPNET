package com.ipnet.blservice;

import com.ipnet.utility.IDNotExistsException;

public interface AccountBLService {
      boolean MoneyMove(String srcAccount , String destAccount , double amount) throws IDNotExistsException;
}
