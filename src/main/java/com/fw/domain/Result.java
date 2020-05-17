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
public class Result implements Serializable {

    private Integer code;

    private String msg;

    private Object data;

    public Result(ResultType resultType,String msg) {

        this.code = resultType.getCode();

        this.msg = msg;
    }
}
