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
public interface EntryMapper {

    /**
     * find entryList by group id
     *
     * @param groupId is
     * @return entry list
     */
    @Select("select * from entry where group_id = #{groupId}")
    @Results(id = "entryMap", value = {
            @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "judges_id", property = "judgesId", jdbcType = JdbcType.INTEGER)
    })
    List<Entry> findEntryListByGroupId(@Param("groupId") Integer groupId);


    /**
     * find entryList by judges id
     *
     * @param judgesId is
     * @return entry list
     */
    @Select("select * from entry where judges_id = #{judgesId}")
    @ResultMap(value = "entryMap")
    List<Entry> findEntryListByJudgesId(@Param("judgesId") Integer judgesId);

    /**
     * find all by name
     * @param entry
     * @return list
     * */
    @ResultMap(value = "entryMap")
    @Select("select * from entry where entryName = #{entryName}")
    List<Entry> findAllByName(Entry entry);

    /**
     * find all by id
     * @param entry
     * @return
     * */
    @ResultMap(value = "entryJudges")
    @Select("select * from entry where id =#{id}")
    @Results({
            @Result(column = "group_id",property = "group",one = @One(select = "com.fw.mapper.GroupMapper.findOneReById"))
    })
    List<Entry> findAllById(Entry entry);



    /**
     * find one by id
     * @param entry entity
     * @return entry
     * */
    @Select("select * from entry where id =#{id}")
    @Results(id = "entryJudges",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "group_id",property = "groupId"),
            @Result(column = "track_id",property = "trackId"),
            @Result(column = "grouping_id",property = "groupingId"),
            @Result(column = "id",property = "files",many = @Many(select = "com.fw.mapper.FileMapper.findAllByEntryId")),
    })
    Entry findOneById(Entry entry);

    /**
     * find one by id
     * @param  id
     * @return entry
     * */
    @Select("select * from entry where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "group_id",property = "group",one = @One(select = "com.fw.mapper.GroupMapper.findOneReById")),
            @Result(column = "track_id",property = "trackId"),
            @Result(column = "grouping_id",property = "gradings",many = @Many(select = "com.fw.mapper.GradingGroupingMapper.findAllByGroupingId")),
            @Result(column = "id",property = "files",many = @Many(select = "com.fw.mapper.FileMapper.findAllByEntryId")),
    })
    Entry findOneReById(@Param("id") Integer id);

    /**
     * find one by name
     * @param entry entity
     * @return entity
     * */
    @ResultMap(value = "entryMap")
    @Select("select * from entry where entryName = #{entryName} ")
    Entry findOneByName(Entry entry);


    /**
     * find
     * @return list
     * */
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "track_id",property = "track",one = @One(select = "com.fw.mapper.TrackMapper.findReOneById")),
            @Result(column = "grouping_id",property = "grouping",one = @One(select = "com.fw.mapper.GroupingMapper.findOneById")),
            @Result(column = "id",property = "entryJudges",many = @Many(select = "com.fw.mapper.EntryJudgesMapper.findAllByEntryId")),
            @Result(column = "group_id",property = "group",one = @One(select = "com.fw.mapper.GroupMapper.findOneReById"))
    })
    @Select("select * from entry")
    List<Entry> findAll();


    /**
     * add new entry
     * @param entry entity
     * */
    @Insert("insert into entry(entryName,entryExplanation,createdTime,group_id,track_id,grouping_id,instructor) values(#{entryName},#{entryExplanation},#{createdTime},#{groupId},#{trackId},#{groupingId},#{instructor})")
    void addEntryMapper(Entry entry);

    /**
     * update entry info
     * @param entry entity
     * */
    @Update("update entry set entryName=#{entryName},entryExplanation=#{entryExplanation},createdTime=#{createdTime},group_id=#{groupId},track_id=#{trackId},grouping_id=#{groupingId} where id =#{id}")
    void updateEntry(Entry entry);

    /**
     * delete entry record
     * @param id entry id
     * */
    @Delete("delete from entry where id =#{id}")
    void deleteEntryById(@Param("id") Integer id);

}
