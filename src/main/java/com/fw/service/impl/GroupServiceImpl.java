package com.fw.service.impl;

import com.fw.domain.Group;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.Role;
import com.fw.mapper.GroupMapper;
import com.fw.service.GroupService;
import com.fw.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            return new Result(ResultType.Has_Registered);
        }

        groupMapper.addGroup(group);

        return new Result(ResultType.Success);
    }

    @Override
    public Result findOneByEmailAndPassword(Group group) {

        List<Group> allByEmail = groupMapper.findAllByEmail(group);

        Map<String,Object> data = new HashMap<>(4);

        if(allByEmail.size()>0){

            Group oneByEmailAndPassword = groupMapper.findOneByEmailAndPassword(group);

            if(oneByEmailAndPassword==null){

                return new Result(ResultType.WrongPassWord,"密码错误",data);
            }

            data.put("token", TokenUtil.sign(oneByEmailAndPassword));

            data.put("role", Role.Group.getType());

            data.put("info",oneByEmailAndPassword);
        }else {
            return new Result(ResultType.Unregistered,"账号未注册",data);
        }


        return new Result(ResultType.Success,"登陆成功",data);
    }

    @Override
    public Result updateGroupInfo(Group group) {

        List<Group> allByEmail = groupMapper.findAllById(group);

        if(allByEmail.size()>0){

            groupMapper.updateGroup(group);

            return new Result(ResultType.Success);
        }

        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findAll() {

        List<Group> all = groupMapper.findAll();

        if(all.size()>0){
            return new Result(ResultType.Success,all);
        }

        return new Result(ResultType.NotFind);
    }

    @Override
    public Result deleteById(Group group) {

        List<Group> allById = groupMapper.findAllById(group);

        if(allById.size()>0){
            groupMapper.deleteGroup(group.getId());
            return new Result(ResultType.Success);
        }
        return new Result(ResultType.NotFind);
    }
}
