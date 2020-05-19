package com.fw.service;

import com.fw.domain.Entry;
import com.fw.domain.Group;
import com.fw.domain.Judges;
import com.fw.domain.Result;

/**
 * @author yqf
 */
public interface EntryService {
    /**
     * add entry
     * @param entry entity
     * @return Result
     * */
    Result addEntry(Entry entry);

    /**
     * delete entry
     * @param id
     * @return result
     * */
    Result deleteEntry(Integer id);

    /**
     * update entry
     * @param entry
     * @return Result
     * */
    Result updateEntry(Entry entry);

    /**
     * find entry list
     * @param group
     * @return result
     * */
    Result findOneByGroupId(Group group);

    /**
     * find entry list
     * @param  judges
     * @return result
     * */
    Result findOneByJudgesId(Judges judges);

    /***
     * find all
     * @return result
     * */
    Result findAll();
}
