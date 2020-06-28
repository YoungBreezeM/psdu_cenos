package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.omg.PortableInterceptor.INACTIVE;

/**
 * @author yqf
 */



public enum ResultType {
    /**基础错误类型*/
    Success(0,"查询成功"),
    AddSuccess(0,"添加成功"),
    UpdateSuccess(0,"更新成功"),
    DeleteSuccess(0,"删除成功"),
    /**注册*/
    Has_Registered(100,"对象已经存在"),
    Unregistered(101,"未注册"),
    UnRole(102,"角色错误"),
    WrongPassWord(103,"密码错误"),
    NotFind(104,"无法找到对象"),
    EmptyData(105,"查找数据为空"),
    /**group*/
    EmailHasRegistered(201,"邮箱已被注册"),
    GroupNameHasRegistered(202,"团队名已经存在"),
    /**home*/
    ResolveSuccess(0,"解析成功"),
    /**file */
    UploadFileSuccess(0,"文件上传成功"),
    DeleteFileSuccess(0,"文件删除成功");

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
