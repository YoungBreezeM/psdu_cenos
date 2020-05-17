package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    private Integer id;

    @NotBlank(message = "团队不能为空")
    private String groupName;

    @NotBlank(message = "联系人不能为空")
    private String principal;

    @NotBlank(message = "密码不能为空")
    private String password;

    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "手机号码格式错误")
    @NotBlank(message = "手机不能为空")
    private String phone;

    @Email(message = "邮件格式不正确")
    @NotBlank(message = "邮件不能为空")
    private String email;

    @NotBlank(message = "院系不能为空")
    private String department;

}
