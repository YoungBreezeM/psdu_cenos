package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grading implements Serializable {

    @NotNull(groups = {
            Select.class
    },message = "grading id cant empty")
    private Integer id;

    @NotNull(groups = {
            Insert.class,
            Update.class
    },message = "scoreName cant empty")
    private String scoreName;

    @NotNull(groups = {
            Insert.class,
            Update.class
    },message = "value cant empty")
    private Integer value;

    private Integer score;

    private List<Rule> rules;
}
