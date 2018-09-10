package com.ipnet.vo.financevo;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Data
/**
 * 评估
 */
public class EvaluationVO {

    private String patentID; //专利ID
    private String url; //文件url
    private int evaluation; //经济价值

}
