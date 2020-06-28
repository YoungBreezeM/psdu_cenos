package com.fw.service;

import com.fw.domain.*;

/**
 * @author yqf
 */
public interface GroupingService {

    /**
     * find all
     * @return rs
     * */
    Result findAll();

    /**
     * add new record
     * @param grouping
     * @return rs
     * */
    Result addRecord(Grouping grouping);

    /**
     * delete one
     * @param id
     * @return rs
     * */
    Result deleteRecord(Integer id);

    /**
     * update
     * @param grouping
     * @return rs
     * */
    Result updateRecord(Grouping grouping);
}
