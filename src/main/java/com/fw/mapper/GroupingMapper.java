package com.fw.mapper;

import com.fw.domain.Grouping;
import com.fw.domain.Track;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface GroupingMapper {

    /**
     * find about track
     * @param id
     * @return list
     * */
    @Select("select * from grouping where id =#{id}")
    Grouping findOneById(@Param("id") Integer id);

    /**
     * find about track
     * @param id
     * @return list
     * */
    @Select("select groupingName from grouping where id =#{id}")
    String findOneNameById(@Param("id") Integer id);

    /**
     * find all
     * @return list
     * */
    @Select("select * from grouping")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "id",property = "gradingList",many = @Many(select = "com.fw.mapper.GradingGroupingMapper.findAllByGroupingId"))
    })
    List<Grouping> findAll();

    /**
     * add new record
     * @param grouping
     * */
    @Insert("insert into grouping(groupingName) values(#{groupingName})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(Grouping grouping);

    /**
     * delete one record
     * @param id
     * */
    @Delete("delete from grouping where id= #{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update one Record
     * @param grouping
     * */
    @Update("update grouping set groupingName =#{groupingName} where id =#{id}")
    void updateRecord(Grouping grouping);
}
