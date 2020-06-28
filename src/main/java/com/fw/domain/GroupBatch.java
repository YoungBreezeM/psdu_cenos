package com.fw.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
public class GroupBatch implements Serializable {

    private List<Group> groups;
}
