package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yqf
 */



public enum ResultType {

    /**
     * success
     * */
    Success(0),
    /**
     * 参数不完整
     * */
    Incomplete_parameters(1),

    /**
     * 已注册
     * */
    Has_Registered(2),

    /**
     * 未注册
     * */
    Unregistered(3),

    /**
     * 角色错误
     * */
    UnRole(4),

    /**
     * 密码错误
     * */
    WrongPassWord(5);

    private Integer code;

    public Integer getCode() {
        return code;
    }

    ResultType(Integer code) {
        this.code = code;
    }
}
