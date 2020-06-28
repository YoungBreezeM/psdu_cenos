package com.fw.mapper;

import com.fw.domain.Entry;
import com.fw.domain.EntryScore;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface EntryScoreMapper {

    /**
     * add record
     * @param entryScore
     * */
    @Insert("insert into entry_score(grading_id,entry_judges_id,score) values(#{gradingId},#{entryJudgesId},#{score})")
    void addEntryScore(EntryScore entryScore);

    /**
     * find one
     * @param entryScore
     * @return entryScore
     * */
    @Select("select * from entry_score where grading_id =#{gradingId} and entry_judges_id = #{entryJudgesId}")
    EntryScore findOneByGradingIdAndEntryJudgesId(EntryScore entryScore);

    /**
     * update score
     * @param entryScore
     * */
    @Update("update entry_score set score =#{score} where grading_id =#{gradingId} and entry_judges_id = #{entryJudgesId}")
    void updateScore(EntryScore entryScore);

    /**
     * find all
     * @param entryScore
     * @return list
     * */
    @Select("select * from entry_score where entry_judges_id = #{entryJudgesId}")
    @Results(id = "entryScoreMap",value = {
            @Result(column = "grading_id",property = "gradingId"),
            @Result(column = "entry_judges_id",property = "entryJudgesId"),
    })
    List<EntryScore> findAllByEntryJudgesId(EntryScore entryScore);

    /**
     * find all by entryJudgesId
     * @param id
     * @return list
     * */
    @ResultMap("entryScoreMap")
    @Select("select * from entry_score where entry_judges_id = #{entryJudgesId}")
    List<EntryScore> findAllByEJId(@Param("entryJudgesId") Integer id);
}
