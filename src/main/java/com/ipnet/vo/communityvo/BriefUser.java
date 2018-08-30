package com.ipnet.vo.communityvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BriefUser {
    private String userID;
    private String nickname;
    private int releasedpost;
    private int fans;
}
