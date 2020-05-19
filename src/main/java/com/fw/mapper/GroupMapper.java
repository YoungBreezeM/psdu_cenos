package com.fw.mapper;

import com.fw.domain.Admin;
import com.fw.domain.Group;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yqf
 */
@Mapper
@Repository
public interface GroupMapper {

    /**
     * findOneGroup
     * @param group looking forward admin entity
     * @return Admin entity
     * */
    @Select("select * from join_group where email=#{email} and password=#{password}")
    Group findOneByEmailAndPassword(Group group);

    /**
     * find All Group
     * @param
     * @return group list
     * */
    @Select("select * from join_group")
    List<Group> findAll();

    /**
     * findAll by email
     * @param group entity
     * @return list
     * */
    @Select("select * from join_group where email =#{email}")
    List<Group> findAllByEmail(Group group);

    /**
     * find one by id
     * @param group entity
     * @return group
     * */
    @Select("select * from join_group where id =#{id}")
    List<Group> findAllById(Group group);

    /**
     * add group
     * @param group entity
     * */
    @Insert("insert into join_group(groupName,principal,password,phone,email,department) values(#{groupName},#{principal},#{password},#{phone},#{email},#{department})")
    void addGroup(Group group);

    /**
     * update group info
     * @param  group entity
     * */
    @Update("update join_group set groupName=#{groupName},principal=#{principal},password=#{password},phone=#{phone},email=#{email},department=#{department} where id =#{id}")
    void updateGroup(Group group);

    /**
     * delete group record
     * @param id groupId
     * */
    @Delete("delete from join_group where id =#{id}")
    void deleteGroup(@Param("id") Integer id);
}
