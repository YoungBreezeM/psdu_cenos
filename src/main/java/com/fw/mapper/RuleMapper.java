package com.fw.mapper;

import com.fw.domain.Rule;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface RuleMapper {

    /**
     *find all rule by gradingId
     * @param id grading id
     * @return list
     * */
    @Select("select * from rule where grading_id =#{gradingId}")
    @Results(id = "ruleMap",value = {
            @Result(column = "grading_id", property = "gradingId", jdbcType = JdbcType.INTEGER)
    })
    List<Rule> findAllByGradingId(@Param("gradingId") Integer id);

    /**
     * add new rule
     * @param rule entity
     * */
    @Insert("insert into rule(ruleContent,grading_id) values(#{ruleContent},#{gradingId})")
    void addRule(Rule rule);

    /**
     * update rule
     * @param rule entity
     * */
    @Update("update rule set ruleContent=#{ruleContent} where id =#{id}")
    void updateRule(Rule rule);

    /**
     * delete rule
     * @param rule
     * */
    @Delete("delete from rule where id =#{id}")
    void deleteRule(Rule rule);
}
