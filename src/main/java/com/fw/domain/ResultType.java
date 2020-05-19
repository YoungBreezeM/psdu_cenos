package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author yqf
 */



public enum ResultType {

    /**
     * success
     * */
    Success(0,"操作成功"),
    /**
     * 参数不完整
     * */
    IncompleteParameters(1,"参数缺失"),

    Has_Registered(2,"对象已经存在"),
    /**
     * 未注册
     * */
    Unregistered(3,"未注册"),

    /**
     * 角色错误
     * */
    UnRole(4,"角色错误"),

    /**
     * 密码错误
     * */
    WrongPassWord(5,"密码错误"),

    /**
     * 不能找到
     * */
    NotFind(6,"无法找到对象"),

    /**
     * 数据为空
     * */
    EmptyData(7,"数据为空");

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultType(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
