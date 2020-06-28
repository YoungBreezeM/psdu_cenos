package com.fw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
public class Track implements Serializable {

    private Integer id;

    private String trackName;

    private List<Grouping> groupings;

}
