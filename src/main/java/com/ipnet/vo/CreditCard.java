package com.ipnet.vo;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCard {
    String cardId;
    String bank;

}
