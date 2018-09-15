package com.ipnet.vo.recvo;

import com.ipnet.bl.patentpoolbl.PoolHelper;
import com.ipnet.entity.Rec.PatentOrManagerRec;
import com.ipnet.utility.IDNotExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class ManagerRecVO {

    @Autowired
    private PoolHelper poolHelper;


    private String rec_id;
    private String manager_url;
    private String manager_name;
    private int patent_num;
    private String manager_intro;

    public ManagerRecVO(){}
    public ManagerRecVO(PatentOrManagerRec patentOrManagerRec) throws IDNotExistsException {
        this.rec_id=patentOrManagerRec.getRec_id();
        this.manager_url=poolHelper.receivePoolURL(patentOrManagerRec.getId());
        this.manager_name=poolHelper.receivePoolName(patentOrManagerRec.getId());
        this.patent_num=poolHelper.receivePoolPatentsNum(patentOrManagerRec.getId());
        this.manager_intro=poolHelper.receivePoolProfile(patentOrManagerRec.getId());
    }
}
