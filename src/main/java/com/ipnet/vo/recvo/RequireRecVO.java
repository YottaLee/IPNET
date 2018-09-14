package com.ipnet.vo.recvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequireRecVO {

    private String id;

    private String buyer_url;
    private String buyer_name;
    private String require_name;
    private Date day;
    private String detail;
}
