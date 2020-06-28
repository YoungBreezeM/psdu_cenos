package com.fw.service;

import com.fw.domain.Grading;
import com.fw.domain.Result;
import com.fw.domain.Rule;


/**
 * @author yqf
 */
public interface GradingService {

    /**
     * find all
     * @return list
     * */
    Result findAll();

    /**
     * find one by id
     * @param grading entity
     * @return grading
     * */
    Result findOneById(Grading grading);

    /**
     * add new grading
     * @param grading entity
     * @return rs
     * */
    Result addGrading(Grading grading);

    /**
     * update grading
     * @param grading entity
     * @return rs
     * */
    Result updateGrading(Grading grading);

    /**
     * delete grading
     * @param grading entity
     * @return rs
     * */
    Result deleteGrading(Grading grading);

    /**
     * delete rule
     * @param rule
     * @return result
     * */
    Result deleteRule(Rule rule);
}
