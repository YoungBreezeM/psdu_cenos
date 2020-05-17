package com.fw.domain;

import lombok.Data;

import java.io.Serializable;


/**
 * @author yqf
 */

@Data
public class Judges implements Serializable {

    private Integer id;

    private String judgesName;

    private String password;

    private String email;

}
