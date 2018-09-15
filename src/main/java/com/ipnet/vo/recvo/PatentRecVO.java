package com.ipnet.vo.recvo;

import com.ipnet.bl.patentbl.PatentHelper;
import com.ipnet.entity.Rec.PatentOrManagerRec;
import com.ipnet.utility.IDNotExistsException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class PatentRecVO {

    @Autowired
    PatentHelper patentHelper;

    private String id;

    private String patent_url;
    private String patent_name;
    private String patent_type;
    private String brief_intro;

    public PatentRecVO(){}
    public PatentRecVO(PatentOrManagerRec patentOrManagerRec) throws IDNotExistsException {
        this.id=patentOrManagerRec.getRec_id();
        this.patent_url=patentHelper.receivePatentURL(patentOrManagerRec.getId());
        this.patent_name=patentHelper.receivePatentName(patentOrManagerRec.getId());
        this.patent_type=patentHelper.receivePatentType(patentOrManagerRec.getId());
        this.brief_intro=patentHelper.receivePatentProfile(patentOrManagerRec.getId());
    }

}
