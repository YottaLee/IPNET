package com.ipnet.vo.recvo;

import com.ipnet.blservice.UserBLService;
import com.ipnet.entity.Rec.RequireRec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Data
@AllArgsConstructor
public class RequireRecVO {

    @Autowired
    private UserBLService userBLService;

    private String id;

    private String buyer_url;
    private String buyer_name;
    private String require_name;
    private Date day;
    private String detail;

    public RequireRecVO(){}

    public RequireRecVO(RequireRec requireRec){
        this.id=requireRec.getRec_id();
        this.buyer_url=userBLService.getImageUrl(requireRec.getBuyer_id());
        //To Do
        this.buyer_name="";
        this.require_name=requireRec.getRequire_name();
        this.day=requireRec.getDay();
        this.detail=requireRec.getDetail();

    }
}
