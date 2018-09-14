package com.ipnet.vo.recvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManagerRecVO {
    private String rec_id;
    private String manager_url;
    private String manager_name;
    private int patent_num;
    private String manager_intro;
}
