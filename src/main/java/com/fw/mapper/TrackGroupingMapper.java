package com.fw.mapper;

import com.fw.domain.Grouping;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface TrackGroupingMapper {

    /**
     * get all by track id
     * @param trackId
     * @return trackGrouping
     * */
    @Select("select * from track_grouping where track_id = #{trackId}")
    @Results({
            @Result(column = "grouping_id",property = "id"),
            @Result(column = "grouping_id",property = "groupingName",one = @One(select = "com.fw.mapper.GroupingMapper.findOneNameById"))
    })
    List<Grouping> getOneByTrackId(@Param("trackId") Integer trackId);


    /**
     * add record
     * @param groupingId
     * @param trackId
     * */
    @Insert("insert into track_grouping(track_id,grouping_id) values(#{trackId},#{groupingId})")
    void addRecord(@Param("trackId") Integer trackId,@Param("groupingId") Integer groupingId);

    /***
     * delete one record by track id
     * @param trackId
     *
     * */
    @Delete("delete from track_grouping where track_id =#{trackId}")
    void deleteRecordByTrackId(@Param("trackId") Integer trackId);
}
