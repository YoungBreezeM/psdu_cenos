package com.fw.service.impl;

import com.fw.domain.Judges;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.Role;
import com.fw.mapper.JudgesMapper;
import com.fw.service.JudgesService;
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
public class JudgesServiceImpl implements JudgesService {

    @Autowired
    private JudgesMapper judgesMapper;


    @Override
    public Result findOneByEmailAndPassword(Judges judges) {

        List<Judges> oneByEmail = judgesMapper.findOneByEmail(judges);

        Map<String,Object> data = new HashMap<>(4);

        if(oneByEmail.size()>0){

            Judges oneByEmailAndPassword = judgesMapper.findOneByEmailAndPassword(judges);

            if(oneByEmailAndPassword==null){
                return new Result(ResultType.WrongPassWord,"密码错误",data);
            }

            data.put("role", Role.Judges.getType());

        }else {
            return new Result(ResultType.Unregistered,"账号未注册",data);
        }


        return new Result(ResultType.Success,"登陆成功",data);
    }


    @Override
    public Result findAll() {
        List<Judges> all = judgesMapper.findAll();

        if(all.size()>0){
            return new Result(ResultType.Success,all);
        }

        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findOneById(Judges judges) {

        Judges oneById = judgesMapper.findOneById(judges);
        if(oneById!=null){
            return new Result(ResultType.Success,oneById);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result deleteById(Judges judges) {
        Judges oneById = judgesMapper.findOneById(judges);
        if(oneById!=null){
            judgesMapper.deleteJudges(judges.getId());
            return new Result(ResultType.Success);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result updateRecord(Judges judges) {
        Judges oneById = judgesMapper.findOneById(judges);

        if(oneById!=null){
            judgesMapper.updateJudges(judges);
            return new Result(ResultType.Success);
        }

        return new Result(ResultType.NotFind);
    }

    @Override
    public Result addRecord(Judges judges) {

        List<Judges> oneByEmail = judgesMapper.findOneByEmail(judges);

        if(oneByEmail.size()>0){
            return new Result(ResultType.Has_Registered);
        }

        judgesMapper.addJudges(judges);

        return new Result(ResultType.Success);
    }
}
