package com.ipnet.vo.financevo;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

/**
 * 贷款
 * @author Panxy
 */
public class Loan {

    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人
    private String bank; //银行名称
    private int money; //金额
    private String time; //期限
    private int evaluation; //评估经济价值结果

}
