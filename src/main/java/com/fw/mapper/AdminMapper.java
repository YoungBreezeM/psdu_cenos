package com.fw.mapper;

import com.fw.domain.Admin;
import org.apache.ibatis.annotations.*;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Repository
@Mapper
public interface AdminMapper {

    /**
     * findOneAdmin
     * @param admin looking forward admin entity
     * @return Admin entity
     * */
    @Select("select * from admin where adminName=#{adminName} and password=#{password}")
    Admin findOneByNameAndPassword(Admin admin);

    /***
     * find one by name
     * @param admin entity
     * @return admin list
     * */
     @Select("select * from admin where adminName=#{adminName}")
     List<Admin> findOneByName(Admin admin);

     /**
      * find all by id
      * @param admin entity
      * @return list
      * */
     @Select("select * from admin where id =#{id}")
     List<Admin> findAllById(Admin admin);

    /**
     * find All admin
     * @return admin list
     * */
    @Select("select * from admin")
    List<Admin> findAll();

    /**
     * add admin
     * @param admin entity
     * */
    @Insert("insert into admin(adminName,password) values(#{adminName},#{password})")
    void addAdmin(Admin admin);

    /**
     * update admin info
     * @param admin entity
     * */
    @Update("update admin set adminName = #{adminName},password = #{password} where id =#{id}")
    void updateAdmin(Admin admin);

    /**
     * delete admin record
     * @param id admin id
     * */
    @Delete("delete from admin where id =#{id}")
    void deleteAdmin(@Param("id") Integer id);

}
