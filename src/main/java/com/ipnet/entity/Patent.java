package com.ipnet.entity;

import com.ipnet.enums.Patent_state;
import com.ipnet.enums.Patent_type;
import com.ipnet.enums.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patent")
public class Patent {
    @Id
    private String patent_id;//专利号
    private String pool_id; //专利池号
    private String patent_name;//专利名称
    private String patent_holder;//专利持有者
    private Patent_state state;//专利状态
    private Date apply_date;//申请时间
    private String valid_period;//有效期限
    private Region region;//所属地区
    private Patent_type patent_type;//专利类别

    @ElementCollection(targetClass = String.class)
    private List<String> invitationPoolIdList;//邀请本专利入池的专利池列表

    public void deleteInvitationFromPool(String patentPoolId){
        if (this.invitationPoolIdList == null || this.invitationPoolIdList.size()==0){
            return;
        }
        this.invitationPoolIdList.remove(patentPoolId);
    }

    public void addInvitationFromPool(String patentPoolId){
        this.invitationPoolIdList.add(patentPoolId);
    }


}
