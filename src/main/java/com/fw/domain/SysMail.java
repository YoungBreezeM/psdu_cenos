package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMail implements Serializable {

    private Integer id;

    @NotNull(message = "服务器不可为空")
    private String host;

    @NotNull(message = "邮箱不可为空")
    private String userName;

    @NotNull(message = "授权码不可为空")
    private String password;

    @NotNull(message = "协议类型不可为空")
    private String protocol;

}
