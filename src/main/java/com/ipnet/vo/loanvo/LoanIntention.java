package com.ipnet.vo.loanvo;

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
 * 专利意向
 * @author Panxy
 */
public class LoanIntention {

    private String patentID;//专利ID
    private String person; //专利持有人
    private int money; //意向金额
    private String time; //意向时间
    private int evaluation; //评估经济价值结果

}
