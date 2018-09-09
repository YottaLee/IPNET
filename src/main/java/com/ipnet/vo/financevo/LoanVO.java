package com.ipnet.vo.financevo;

import lombok.*;
import org.springframework.stereotype.Component;

/**
 * 贷款
 * @author Panxy
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class LoanVO {

    private String id;//贷款ID，由时间和专利号构成
    private String patentID;//专利ID
    private String patent;//专利名称
    private String person; //专利持有人
    private String bank; //银行名称
    private int money; //金额
    private String time; //期限
    private int evaluation; //评估经济价值结果

}
