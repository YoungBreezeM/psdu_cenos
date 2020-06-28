package com.fw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
public class Grouping implements Serializable {

    private Integer id;

    private String groupingName;

    private List<Track> tracks;

    private List<Grading> gradingList;
}
