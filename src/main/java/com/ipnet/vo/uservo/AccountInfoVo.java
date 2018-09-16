package com.ipnet.vo.uservo;

import com.ipnet.enums.CompanyType;
import com.ipnet.enums.Identity;
import com.ipnet.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfoVo {
    private List<String> acountId;       //银行卡号
    private String userId;
    private double balance;       //账户余额;
}
