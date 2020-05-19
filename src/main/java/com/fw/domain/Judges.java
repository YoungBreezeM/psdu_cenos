package com.fw.domain;

import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * @author yqf
 */

@Data
public class Judges implements Serializable {

    @NotNull(groups = {
            Update.class,
            Select.class
    },message = "评委id不能为空")
    private Integer id;

    @NotNull(groups = {
            Insert.class,
            Update.class
    },message = "用户名不为空")
    private String judgesName;

    @NotNull(groups = {
            Insert.class,
            Update.class
    },message = "密码不为空")
    private String password;


    @Email(groups = {
            Insert.class,
            Update.class
    },message = "邮箱格式不正确")
    private String email;

}
