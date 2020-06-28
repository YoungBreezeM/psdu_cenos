package com.fw.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.omg.PortableInterceptor.INACTIVE;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author yqf
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryJudges implements Serializable {

    @NotNull(groups = {
            Select.class,
            Update.class
    },message = "entry_judges id cant empty")
    private Integer id;

    @NotNull(groups ={
            Insert.class,
            Delete.class
    },message = "entry id cant empty")
    private Integer entryId;

    @NotNull(groups = {
            Insert.class,
            Delete.class
    },message = "judges id cant empty")
    private Integer judgesId;

    private String remark;

    private List<Grading> gradings;

    private List<EntryScore> entryScores;

    private Judges judges;

    private Entry entry;


}
