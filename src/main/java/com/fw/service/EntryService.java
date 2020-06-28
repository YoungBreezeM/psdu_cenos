package com.fw.service;

import com.fw.domain.*;

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
     * @param  entryJudges
     * @return result
     * */
    Result findAllByJudgesId(EntryJudges entryJudges);

    /***
     * find all
     * @return result
     * */
    Result findAll();

    /**
     * find one by id
     * @param entry
     * @return result
     * */
    Result findOneById(Entry entry);

    /**
     * add entry for judges
     * @param entryJudges
     * @return result
     * */
    Result addEntryJudges(EntryJudges entryJudges);

    /**
     * delete record by entry id
     * @param entryJudges
     * @return result
     * */
    Result deleteEntryJudges(EntryJudges entryJudges);

    /**
     * delete by entry id and judges id
     * @param entryJudges entity
     * @return return
     * */
    Result deleteByEntryIdAndJudges(EntryJudges entryJudges);

    /**
     * update remark
     * @param entryJudges
     * @return int
     * */
    Result updateRemarkAndScore(EntryJudges entryJudges);

    /**
     * find remark
     * @param entryJudges
     * @return rs
     * */
    Result findRemark(EntryJudges entryJudges);

    /**
     * find score
     * @param entryScore
     * @return rs;
     * */
    Result findAllByEntryJudgesId(EntryScore entryScore);

    /**
     * find all by entry id
     * @param entry
     * @return rs
     * */
    Result findAllByEntryId(Entry entry);

}
