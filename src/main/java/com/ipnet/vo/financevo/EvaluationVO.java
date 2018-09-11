package com.ipnet.vo.financevo;

import lombok.*;

/**
 * 评估
 */
@Data
public class EvaluationVO {

    private String patentID; //专利ID
    private String url; //文件url
    private double evaluation; //经济价值

}
