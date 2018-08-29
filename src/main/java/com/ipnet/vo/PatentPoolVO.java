package com.ipnet.vo;

import com.ipnet.enums.Industry;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * @author lzb
 * @date 2018/7/21 10:28
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class PatentPoolVO {

    private String id;//专利池的ID，作为唯一标识，由系统自动生成

    private String name;//专利池的名字
    private String owner;//专利池的创建者ID
    private String picture;//专利池的头像？的url
    private Industry industry;//池内专利所属行业
    private String description;//专利池简介
    private Date createTime;//专利池的创建时间


    private List<String> managers;//专利池的管理团队

    private List<String> users;//专利池内的专利持有者列表

    private List<String> patents;//专利池内的专利列表

}
