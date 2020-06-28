package com.fw.service.impl;

import com.fw.domain.Admin;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.Role;
import com.fw.mapper.AdminMapper;
import com.fw.service.AdminService;
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

        Map<String,Object> data = new HashMap<>(4);

        if(oneByName.size()>0){

            Admin oneByNameAndPassword = adminMapper.findOneByNameAndPassword(admin);

            if(oneByNameAndPassword==null){
                return new Result(ResultType.WrongPassWord,data);
            }

            data.put("role", Role.Admin.getType());


        }else {
            return new Result(ResultType.Unregistered,"账号未注册",data);
        }
        return new Result(ResultType.Success,"登陆成功",data);
    }

    @Override
    public Result updateAdminInfo(Admin admin) {

        List<Admin> allById = adminMapper.findAllById(admin);

        if (allById.size()>0){
            adminMapper.updateAdmin(admin);
            return new Result(ResultType.Success);
        }

        return new Result(ResultType.NotFind);
    }
}
