package com.fw.service.impl;

import com.fw.domain.Group;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.mapper.GroupMapper;
import com.fw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public Result addGroup(Group group) {

        List<Group> allByEmail = groupMapper.findAllByEmail(group);

        if(allByEmail.size()>0){
            return new Result(ResultType.Has_Registered,"用户已注册");
        }

        groupMapper.addGroup(group);

        return new Result(ResultType.Success,"操作成功");
    }

    @Override
    public Result findOneByEmailAndPassword(Group group) {

        List<Group> allByEmail = groupMapper.findAllByEmail(group);

        if(allByEmail.size()>0){

            Group oneByEmailAndPassword = groupMapper.findOneByEmailAndPassword(group);

            if(oneByEmailAndPassword==null){

                return new Result(ResultType.WrongPassWord,"密码错误");
            }
        }else {
            return new Result(ResultType.Unregistered,"账号未注册");
        }


        return new Result(ResultType.Success,"登陆成功");
    }
}
