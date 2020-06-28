package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {

    @NotNull(groups = {
            Update.class,
            Select.class
    },message = "团队id不为空")
    private Integer id;

    @NotBlank(groups = {
            Insert.class,
            Update.class
    },message = "团队不能为空")
    private String groupName;

    @NotBlank(groups = {
            Update.class,
            Insert.class
    },message = "联系人不能为空")
    private String principal;



    @NotBlank(groups = {
            Update.class,
            Insert.class
    },message = "密码不能为空")
    private String password;

    @Pattern(groups = {
            Update.class,
            Insert.class
    },regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotBlank(groups = {
            Update.class,
            Insert.class
    },message = "手机不能为空")
    private String phone;

    @Email(groups = {
            Update.class,
            Insert.class
    },message = "邮件格式不正确")
    @NotBlank(groups = {
            Update.class,
            Insert.class
    },message = "邮件不能为空")
    private String email;

    @NotBlank(groups = {
            Update.class,
            Insert.class
    },message = "院系不能为空")
    private String department;

}
