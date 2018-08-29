package com.ipnet.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSetRead {
    private String username;
    private ArrayList<String> messageTimeList;
}
