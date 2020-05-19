package com.fw.mapper;

import com.fw.domain.Entry;
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
    @Select("select * from entry where entryName = #{entryName}")
    List<Entry> findAllByName(Entry entry);

    /**
     * find all by id
     * @param entry
     * @return
     * */
    @Select("select * from entry where id =#{id}")

    List<Entry> findAllById(Entry entry);

    /**
     * find one by id
     * @param entry entity
     * @return entry
     * */
    @Select("select * from entry where id =#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "files",many = @Many(select = "com.fw.mapper.FileMapper.findAllByEntryId")),
    })
    Entry findOneById(Entry entry);

    /**
     * find one by name
     * @param entry entity
     * @return entity
     * */
    @Select("select * from entry where entryName = #{entryName} ")
    Entry findOneByName(Entry entry);


    /**
     * find
     * @return list
     * */
    @Select("select * from entry")
    List<Entry> findAll();


    /**
     * add new entry
     * @param entry entity
     * */
    @Insert("insert into entry(entryName,entryExplanation,createdTime,remake,group_id,judges_id) values(#{entryName},#{entryExplanation},#{createdTime},#{remake},#{groupId},#{judgesId}) ")
    void addEntryMapper(Entry entry);

    /**
     * update entry info
     * @param entry entity
     * */
    @Update("update entry set entryName=#{entryName},entryExplanation=#{entryExplanation},createdTime=#{createdTime},remake=#{remake},group_id=#{groupId},judges_id=#{judgesId} where id =#{id}")
    void updateEntry(Entry entry);

    /**
     * delete entry record
     * @param id entry id
     * */
    @Delete("delete from entry where id =#{id}")
    void deleteEntryById(@Param("id") Integer id);

}
