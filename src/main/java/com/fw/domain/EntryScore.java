package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryScore implements Serializable {

    private Integer id;

    private Integer gradingId;

    private Integer entryJudgesId;

    private Integer score;
}
