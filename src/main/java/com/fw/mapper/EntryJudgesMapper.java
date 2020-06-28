package com.fw.mapper;

import com.fw.domain.Entry;
import com.fw.domain.EntryJudges;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface EntryJudgesMapper {

    /**
     * find all by entryId
     * @param entryId e
     * @return list
     * */
    @Select("select * from entry_judges where entry_id =#{entryId}")
    @Results(id = "entryJudgesMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "entry_id", property = "entryId", jdbcType = JdbcType.INTEGER),
            @Result(column = "judges_id", property = "judgesId", jdbcType = JdbcType.INTEGER),
            @Result(column = "judges_id", property = "judges", one=@One(select = "com.fw.mapper.JudgesMapper.findReOneById")),
            @Result(column = "id",property = "entryScores",many = @Many(select = "com.fw.mapper.EntryScoreMapper.findAllByEJId")),
    })
    List<EntryJudges> findAllByEntryId(@Param("entryId") Integer entryId);


    /**
     * find all by entryId
     * @param entryJudges
     * @return list
     * */
    @Select("select * from entry_judges where judges_id =#{judgesId}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "entry_id",property = "entryId"),
            @Result(column = "judges_id", property = "judgesId", jdbcType = JdbcType.INTEGER),
            @Result(column = "entry_id",property = "entry",one = @One(select = "com.fw.mapper.EntryMapper.findOneReById"))
    })
    List<EntryJudges> findAllEntryByJudgesId(EntryJudges entryJudges);

    /**
     * add entry for judges
     * @param entryJudges entity
     * */
    @Insert("insert into entry_judges(entry_id,judges_id) values(#{entryId},#{judgesId})")
    void addEntryJudges(EntryJudges entryJudges);

    /**
     * delete record by entry id
     * @param entryJudges
     * */
    @Delete("delete from entry_judges where entry_id=#{entryId}")
    void deleteByEntryId(EntryJudges entryJudges);

    /**
     * delete record by entry id and judges id
     * @param entryJudges entity
     * */
    @Delete("delete from entry_judges where entry_id=#{entryId} and judges_id=#{judgesId}")
    void deleteByEntryIdAndJudgesId(EntryJudges entryJudges);

    /**
     * update remake
     * @param entryJudges
     * */
    @Update("update  entry_judges set remark =#{remark} where entry_id=#{entryId} and judges_id =#{judgesId}")
    void updateRemark(EntryJudges entryJudges);

    /**
     * find by entry id and judges id
     * @param entryJudges
     * @return entryJudges
     * */
    @Select("select * from entry_judges where entry_id =#{entryId} and judges_id=#{judgesId}")
    @Results({
            @Result(column = "entry_id", property = "entryId", jdbcType = JdbcType.INTEGER),
            @Result(column = "judges_id", property = "judgesId", jdbcType = JdbcType.INTEGER),
            @Result(column = "entry_id",property = "entry",one = @One(select = "com.fw.mapper.EntryMapper.findOneReById"))
    })
    EntryJudges findOneByEntryIdAndJudgesId(EntryJudges entryJudges);


}
