package com.ipnet.entity.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class Account {
      @Id
      private String acountId;       //银行卡号
      private String userId;
      private double balance;       //账户余额
}
