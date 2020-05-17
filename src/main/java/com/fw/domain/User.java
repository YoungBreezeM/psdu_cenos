package com.fw.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author yqf
 */
@Data
public class User implements Serializable {

    @NotBlank(message = "账户不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "身份不能为空")
    private String role;
}
