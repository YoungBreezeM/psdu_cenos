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

    @NotNull(groups = {
            Select.class,
            Insert.class,
            Update.class,
    },message = "团队id不为空")
    private Integer groupId;

    @NotNull(groups ={
            Insert.class,
            Update.class
    },message = "请选择赛道")
    private Integer trackId;

    @NotNull(groups = {
            Update.class,
            Insert.class
    },message = "指导老师不能为空")
    private String instructor;

    @NotNull(groups = {
            Insert.class,
            Update.class
    },message = "请选择分组")
    private Integer groupingId;

    private List<Grading> gradings;

    private String aveScore;

    private Boolean isScore;

    private Group group;

    private Track track;

    private Grouping grouping;

    private List<UploadFile> files;

    private List<EntryJudges> entryJudges;
}
