package com.ipnet.vo.recvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatentRecVO {

    private String id;

    private String patent_url;
    private String patent_name;
    private String patent_type;
    private String brief_intro;

}
