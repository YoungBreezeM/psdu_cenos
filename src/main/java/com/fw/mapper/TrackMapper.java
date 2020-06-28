package com.fw.mapper;

import com.fw.domain.Track;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface TrackMapper {

    /**
     * find all
     * @return list
     * */
    @Select("select * from track")
    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "id",property = "groupings",many = @Many(select = "com.fw.mapper.TrackGroupingMapper.getOneByTrackId")),
    })
    List<Track> findAll();

    /**
     * find one by id
     * @param id
     * @return track
     * */
    @Select("select * from track where id = #{id}")
    Track findReOneById(@Param("id") Integer id);

    /**
     * add new record
     * @param track
     * */
    @Insert("insert into track(trackName) values(#{trackName})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addRecord(Track track);

    /**
     * delete one record
     * @param id
     * */
    @Delete("delete from track where id= #{id}")
    void deleteRecord(@Param("id") Integer id);

    /**
     * update one Record
     * @param track
     * */
    @Update("update track set trackName =#{trackName} where id =#{id}")
    void updateRecord(Track track);
}
