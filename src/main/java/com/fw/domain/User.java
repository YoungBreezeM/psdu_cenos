package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @NotBlank(message = "账户不能为空")
    @Email(message = "邮件格式不正确")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "身份不能为空")
    private String role;
}
