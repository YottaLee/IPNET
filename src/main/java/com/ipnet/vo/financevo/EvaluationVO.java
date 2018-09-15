package com.ipnet.vo.financevo;

import lombok.*;

/**
 * 评估
 */
@Data
public class EvaluationVO {

    private String patentID; //专利ID
    private String patent;//专利名称
    private String rule;//评估原则
    private String tech;//技术价值
    private String result;//评估结果
    private String url; //文件url
    private double evaluation; //经济价值
    private double money; //评估费用

}
