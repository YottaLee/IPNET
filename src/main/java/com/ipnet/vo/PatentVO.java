package com.ipnet.vo;

import com.ipnet.enums.Patent_loan_state;
import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Patent_type;
import com.ipnet.enums.Region;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

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
    private String pool_id; //专利池号
    private String patent_name;//专利名称
    private String patent_holder;//专利持有者
    private Patent_state state;//专利状态

    //换成string类型
    private String apply_date;//申请时间
    private String valid_period;//有效期限
    private Region region;//所属地区
    private String patent_type;//专利类别

    private  String profile;

    private  String url;    //图片的url

    private List<String> invitationPoolIdList;//邀请本专利入池的专利池列表

}
