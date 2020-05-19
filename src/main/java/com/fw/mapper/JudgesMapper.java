package com.fw.mapper;


import com.fw.domain.Judges;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface JudgesMapper {
    /**
     * findOneJudges
     * @param judges looking forward Judges entity
     * @return Admin entity
     * */
    @Select("select * from judges where email=#{email} and password=#{password}")
    Judges findOneByEmailAndPassword(Judges judges);

    /***
     * find one by email
     * @param judges entity
     * @return judges
     * */
    @Select("select * from judges where email =#{email}")
    List<Judges> findOneByEmail(Judges judges);

    /**
     * find one by id
     * @param judges
     * @return list
     * */
    @Select("select * from judges where id =#{id}")
    List<Judges> findOneById(Judges judges);

    /**
     * find All judges
     * @param
     * @return judges list
     * */
    @Select("select * from judges")
    List<Judges> findAll();

    /**
     * add judges
     * @param judges entity
     * */
    @Insert("insert into judges(judgesName,password,email) values(#{judgesName},#{password},#{email})")
    void addJudges(Judges judges);

    /**
     * update judges info
     * @param judges entity
     * */
    @Update("update judges set email = #{email},password = #{password} where id =#{id}")
    void updateJudges(Judges judges);

    /**
     * delete judges record
     * @param id judges id
     * */
    @Delete("delete from judges where id=#{id}")
    void deleteJudges(@Param("id") Integer id);
}
