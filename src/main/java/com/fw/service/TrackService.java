package com.fw.service;

import com.fw.domain.Entry;
import com.fw.domain.Result;
import com.fw.domain.Track;
import org.springframework.stereotype.Service;

/**
 * @author yqf
 */

public interface TrackService {
    /**
     * find all
     * @return rs
     * */
    Result findAll();

    /**
     * add new record
     * @param track
     * @return rs
     * */
    Result addRecord(Track track);

    /**
     * delete one
     * @param id
     * @return rs
     * */
    Result deleteRecord(Integer id);

    /**
     * update
     * @param track
     * @return rs
     * */
    Result updateRecord(Track track);
}
