package com.fw.mapper;

import com.fw.domain.Grading;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface GradingMapper {

    /**
     * find all grading
     * @return list
     * */
    @Select("select * from grading")
    @Results(id = "gradingMap",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "id",property = "rules",many = @Many(select = "com.fw.mapper.RuleMapper.findAllByGradingId")),
    })
    List<Grading> findAll();

    /**
     * find One by id
     * @param grading entity
     * @return grading
     * */
    @Select("select * from grading where id =#{id}")
    @ResultMap("gradingMap")
    Grading findOneById(Grading grading);

    /**
     * find one name by id
     * @param id
     * @return string
     * */
    @Select("select scoreName from grading where id = #{id}")
    String findOneNameById(@Param("id") Integer id);

    /**
     * find one Value by id
     * @param id
     * @return string
     * */
    @Select("select value from grading where id = #{id}")
    Integer findOneValueById(@Param("id") Integer id);

    /**
     * add new grading
     * @param grading entity
     * */
    @Insert("insert into grading(scoreName,value) values(#{scoreName},#{value})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addGrading(Grading grading);

    /**
     * update grading
     * @param grading
     * */
    @Update("update grading set scoreName =#{scoreName},value=#{value} where id =#{id}")
    void updateGrading(Grading grading);

    /**
     * delete grading
     * @param grading entity
     * */
    @Delete("delete from grading where id =#{id}")
    void deleteGrading(Grading grading);

}
