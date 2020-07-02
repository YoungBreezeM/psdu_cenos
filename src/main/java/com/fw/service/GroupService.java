package com.fw.service;

import com.fw.domain.Group;
import com.fw.domain.Result;

import java.util.List;

/**
 * @author yqf
 */
public interface GroupService {
    /**
     * 添加团队
     * @param group 团队entity
     * @return Result
     * */
    Result addGroup(Group group);

    /**
     * 查看group 是否注册
     * @param group entity
     * @return list
     * */
    Result findOneByEmailAndPassword(Group group);

    /**
     * update group info
     * @param group entity
     * @return result
     * */
    Result updateGroupInfo(Group group);

    /**
     * findAll
     * @return result
     * */
    Result findAll();

    /**
     * delete
     * @param group
     * @return result
     * */
    Result deleteById(Group group);

    /**
     * find group by name
     * @param group
     * @return result
     * */
    List<Group> findOneByName(Group group);

    /**
     * find all by Email
     * @param group
     * @return List
     * */
    List<Group> findAllByEmail(Group group);

}
