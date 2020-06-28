package com.fw.service;

import com.fw.domain.Admin;
import com.fw.domain.Judges;
import com.fw.domain.Result;

/**
 * @author yqf
 */
public interface JudgesService {
    /**
     * find one by email and password
     * @param judges entity
     * @return judges
     * */
    Result findOneByEmailAndPassword(Judges judges);


    /**
     * find All
     * @return result
     * */
    Result findAll();

    /**
     * findOne by id
     * @param judges
     * @return result
     * */
    Result findOneById(Judges judges);

    /**
     * delete record
     * @param judges
     * @return result
     * */
    Result deleteById(Judges judges);

    /**
     * update one record
     * @param judges entity
     * @return result
     * */
    Result updateRecord(Judges judges);

    /**
     * add record
     * @param judges
     * @return result
     * */
    Result addRecord(Judges judges);
}
