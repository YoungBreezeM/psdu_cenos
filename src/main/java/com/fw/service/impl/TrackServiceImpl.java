package com.fw.service.impl;

import com.fw.domain.*;
import com.fw.mapper.GroupingMapper;
import com.fw.mapper.TrackGroupingMapper;
import com.fw.mapper.TrackMapper;
import com.fw.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackMapper trackMapper;

    @Autowired
    private TrackGroupingMapper trackGroupingMapper;

    @Override
    public Result findAll() {

        List<Track> all = trackMapper.findAll();

        if(all.size()>0){
            return new Result(ResultType.Success,all);
        }

        return new Result(ResultType.EmptyData);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result addRecord(Track track) {
        trackMapper.addRecord(track);

        List<Grouping> groupings = track.getGroupings();

        if(groupings.size()>0){
            for (Grouping grouping : groupings) {
                trackGroupingMapper.addRecord(track.getId(),grouping.getId());
            }
        }
        return new Result(ResultType.AddSuccess);
    }

    @Override
    public Result deleteRecord(Integer id) {
        trackMapper.deleteRecord(id);
        return new Result(ResultType.DeleteSuccess);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result updateRecord(Track track) {
        trackMapper.updateRecord(track);
        List<Grouping> groupings = track.getGroupings();
        if(groupings.size()>0){
            trackGroupingMapper.deleteRecordByTrackId(track.getId());
            for (Grouping grouping : groupings) {
                trackGroupingMapper.addRecord(track.getId(),grouping.getId());
            }
        }
        return new Result(ResultType.UpdateSuccess);
    }
}
