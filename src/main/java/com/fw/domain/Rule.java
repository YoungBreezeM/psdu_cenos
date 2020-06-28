package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule implements Serializable {

    private Integer id;

    private String ruleContent;

    private Integer gradingId;

}
