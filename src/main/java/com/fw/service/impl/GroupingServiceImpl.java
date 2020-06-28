package com.fw.service.impl;

import com.fw.domain.Grading;
import com.fw.domain.Grouping;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.mapper.GradingGroupingMapper;
import com.fw.mapper.GroupingMapper;
import com.fw.service.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author yqf
 */
@Service
public class GroupingServiceImpl implements GroupingService {

    @Autowired
    private GroupingMapper groupingMapper;

    @Autowired
    private GradingGroupingMapper gradingGroupingMapper;

    @Override
    public Result findAll() {
        List<Grouping> all = groupingMapper.findAll();

        if(all.size()>0){
            return new Result(ResultType.Success,all);
        }
        return new Result(ResultType.EmptyData);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result addRecord(Grouping grouping) {
        groupingMapper.addRecord(grouping);
        List<Grading> gradingList = grouping.getGradingList();
        if(gradingList.size()>0){
            for (Grading grading : gradingList) {
                gradingGroupingMapper.addRecord(grading.getId(),grouping.getId());
            }
        }
        return new Result(ResultType.AddSuccess);
    }

    @Override
    public Result deleteRecord(Integer id) {
         groupingMapper.deleteRecord(id);
         return new Result(ResultType.DeleteSuccess);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result updateRecord(Grouping grouping) {
        groupingMapper.updateRecord(grouping);
        List<Grading> gradingList = grouping.getGradingList();
        if(gradingList.size()>0){
            gradingGroupingMapper.deleteRecordByGroupingId(grouping.getId());
            for (Grading grading : gradingList) {
                gradingGroupingMapper.addRecord(grading.getId(),grouping.getId());
            }
        }
        return new Result(ResultType.UpdateSuccess);
    }
}
