package com.fw.domain;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author yqf
 */

@Data
public class Admin implements Serializable {

    private Integer id;

    private String adminName;

    private String password;
}
