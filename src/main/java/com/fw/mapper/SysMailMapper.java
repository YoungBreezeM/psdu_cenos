package com.fw.mapper;

import com.fw.domain.SysMail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface SysMailMapper {

    /**
     * find one mail config
     * @return entity
     * */
    @Select("select * from sys_mail limit 1")
    SysMail findOne();


    /**
     * update mail config
     * @param sysMail entity
     * */
    @Update("update sys_mail set host =#{host},userName=#{userName},password=#{password},protocol=#{protocol} where id = #{id}")
    void updateMail(SysMail sysMail);
}
