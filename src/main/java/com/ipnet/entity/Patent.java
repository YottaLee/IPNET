package com.ipnet.entity;

import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Patent_type;
import com.ipnet.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patent")
public class Patent {
    @Id
    private String patent_id;//专利号
    private String patent_holder;//专利持有者
    private Patent_state state;//专利状态
    private Date apply_date;//申请时间
    private String valid_period;//有效期限
    private Region region;//所属地区
    private Patent_type patent_type;//专利类别
}
