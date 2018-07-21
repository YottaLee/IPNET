package com.ipnet.entity;

import com.ipnet.enums.Industry;
import lombok.Data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

/**
 * @Author:zhangping
 * @Description:
 * @CreateData: 2018/7/21 1:39
 */

@Entity
@Data
@Table(name = "pool")
public class PatentPool {
    @Id
    private String id;//专利池的ID，作为唯一标识，由系统自动生成

    private String name;//专利池的名字
    private String owner;//专利池的创建者ID
    private String picture;//专利池的头像？的url
    private Industry industry;//池内专利所属行业
    private String description;//专利池简介
    private Date createTime;//专利池的创建时间

    @ElementCollection(targetClass = String.class)
    private List<String> managers;//专利池的管理团队

    @ElementCollection(targetClass = String.class)
    private List<String> users;//专利池内的专利持有者列表

    @ElementCollection(targetClass = String.class)
    private List<String> patents;//专利池内的专利列表
}
