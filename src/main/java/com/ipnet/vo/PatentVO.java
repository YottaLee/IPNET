package com.ipnet.vo;

import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Patent_type;
import com.ipnet.enums.Region;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lzb
 * @date 2018/7/21 10:15
 */
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class PatentVO {

    private String patent_id;//专利号
    private String patent_name;//专利名称
    private String patent_holder;//专利持有者
    private Patent_state state;//专利状态
    private Date apply_date;//申请时间
    private String valid_period;//有效期限
    private Region region;//所属地区
    private Patent_type patent_type;//专利类别

}
