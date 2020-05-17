package com.fw.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

/**
 * @author yqf
 */
@Data
public class Entry implements Serializable {

    private Integer id;

    private String entryName;

    private String entryExplanation;

    private Date createdTime;

    private String filePath;

    private String remake;

    private Integer groupId;

    private Integer judgesId;
}
