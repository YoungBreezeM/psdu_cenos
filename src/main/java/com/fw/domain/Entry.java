package com.fw.domain;

import com.fw.utils.DateUtil;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * @author yqf
 */
@Data
public class Entry implements Serializable {

    @NotNull(groups = {
            Select.class,
            Update.class,
            Delete.class
    },message = "项目id 不能为空")
    private Integer id;

    @NotBlank(groups = {
            Insert.class,
            Update.class,
    },message = "项目名不可为空")
    private String entryName;

    private String entryExplanation;

    private Date createdTime ;

    private String remake;

    @NotNull(groups = {
            Select.class,
            Insert.class,
            Update.class,
    },message = "团队id不为空")
    private Integer groupId;

    @NotNull(groups = {
            Select.class
    },message = "评审id不为空")
    private Integer judgesId;

    private List<UploadFile> files;
}
