package com.fw.service.impl;

import com.fw.domain.Admin;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.mapper.AdminMapper;
import com.fw.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Result findOneByName(Admin admin) {

        return null;
    }

    @Override
    public Result findOneByNameAndPassword(Admin admin) {
        List<Admin> oneByName = adminMapper.findOneByName(admin);


        if(oneByName.size()>0){

            Admin oneByNameAndPassword = adminMapper.findOneByNameAndPassword(admin);

            if(oneByNameAndPassword==null){
                return new Result(ResultType.WrongPassWord,"密码错误");
            }

        }else {
            return new Result(ResultType.Unregistered,"账号未注册");
        }
        return new Result(ResultType.Success,"登陆成功");
    }
}
