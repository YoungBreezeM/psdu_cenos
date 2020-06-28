package com.fw.service.impl;

import com.fw.domain.Grading;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.Rule;
import com.fw.exception.ExceptionHandler;
import com.fw.mapper.GradingMapper;
import com.fw.mapper.RuleMapper;
import com.fw.service.GradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.Response;
import java.util.List;

/**
 * @author yqf
 */
@Service
public class GradingServiceImpl implements GradingService {

    @Autowired
    private GradingMapper gradingMapper;

    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public Result findAll() {

        List<Grading> all = gradingMapper.findAll();

        return new Result(ResultType.Success,all);

    }

    @Override
    public Result findOneById(Grading grading) {
        Grading oneById = gradingMapper.findOneById(grading);
        if(oneById==null){
            return new Result(ResultType.NotFind);
        }
        return new Result(ResultType.Success,oneById);
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class})
    public Result addGrading(Grading grading) {

        gradingMapper.addGrading(grading);

        List<Rule> rules = grading.getRules();

        for (Rule rule : rules) {
            rule.setGradingId(grading.getId());
            ruleMapper.addRule(rule);
        }
        return new Result(ResultType.AddSuccess);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result updateGrading(Grading grading) {

        gradingMapper.updateGrading(grading);
        List<Rule> rules = grading.getRules();
        if(rules.size()>0){
            for (Rule rule : rules) {
                if(rule.getRuleContent()!=null&&rule.getGradingId()==null){
                    rule.setGradingId(grading.getId());
                    ruleMapper.addRule(rule);
                }else {
                    ruleMapper.updateRule(rule);
                }
            }
        }
        return new Result(ResultType.UpdateSuccess);
    }

    @Override
    public Result deleteGrading(Grading grading) {
        gradingMapper.deleteGrading(grading);
        return new Result(ResultType.DeleteSuccess);
    }

    @Override
    public Result deleteRule(Rule rule) {
        ruleMapper.deleteRule(rule);
        return new Result(ResultType.DeleteSuccess);
    }
}
