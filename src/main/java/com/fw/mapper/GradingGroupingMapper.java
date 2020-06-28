package com.fw.mapper;

import com.fw.domain.Grading;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface GradingGroupingMapper {

    /**
     * find all by grouping id
     * @param groupingId
     * @return list
     * */
    @Select("select * from grading_grouping where grouping_id =#{groupingId} ")
    @Results({
            @Result(column = "grading_id",property = "id"),
            @Result(column = "grading_id",property = "scoreName",one = @One(select = "com.fw.mapper.GradingMapper.findOneNameById")),
            @Result(column = "grading_id",property = "value",one = @One(select = "com.fw.mapper.GradingMapper.findOneValueById")),
            @Result(column = "grading_id",property = "rules",many = @Many(select = "com.fw.mapper.RuleMapper.findAllByGradingId"))
    })
    List<Grading> findAllByGroupingId(@Param("groupingId") Integer groupingId);

    /**
     * add record
     * @param groupingId
     * @param gradingId
     * */
    @Insert("insert into grading_grouping(grading_id,grouping_id) values(#{gradingId},#{groupingId})")
    void addRecord(@Param("gradingId") Integer gradingId,@Param("groupingId") Integer groupingId);

    /***
     * delete one record by track id
     * @param groupingId
     * */
    @Delete("delete from grading_grouping where grouping_id =#{groupingId}")
    void deleteRecordByGroupingId(@Param("groupingId") Integer groupingId);
}
