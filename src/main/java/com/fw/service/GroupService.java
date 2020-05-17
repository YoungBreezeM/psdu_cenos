package com.fw.service;

import com.fw.domain.Group;
import com.fw.domain.Result;

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

}
