package com.fw.domain;

import lombok.Data;
import lombok.Getter;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author yqf
 */

@Data
public class Admin implements Serializable {

    @NotNull(groups = {
            Update.class,
            Select.class,
            Delete.class
    },message = "id 不能为空")
    private Integer id;

    @NotNull(groups = {
            Insert.class,
            Update.class,
            Select.class
    },message = "名字不能为空")
    private String adminName;

    @NotNull(groups = {
            Insert.class,
            Update.class,
            Select.class
    },message = "密码不为空")
    private String password;
}
