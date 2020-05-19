package com.fw.service.impl;

import com.fw.domain.*;
import com.fw.mapper.EntryMapper;
import com.fw.mapper.FileMapper;
import com.fw.mapper.GroupMapper;
import com.fw.mapper.JudgesMapper;
import com.fw.service.EntryService;
import com.fw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

/**
 * @author yqf
 */
@Service
public class EntryServiceImpl implements EntryService {

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private JudgesMapper judgesMapper;

    @Autowired
    private FileServiceImpl fileService;

    @Override
    public Result addEntry(Entry entry) {

        List<Entry> allByName = entryMapper.findAllByName(entry);

        if(allByName.size()>0){
            return new Result(ResultType.Has_Registered);
        }

        Group group = new Group();

        group.setId(entry.getGroupId());

        List<Group> allById = groupMapper.findAllById(group);

        if(allById.size()==0){
            return new Result(ResultType.NotFind);
        }

        entryMapper.addEntryMapper(entry);

        if(entry.getFiles()!=null){
            Entry oneByName = entryMapper.findOneByName(entry);
            oneByName.setFiles(entry.getFiles());
            fileService.updateEntryIdById(oneByName);
        }

        return new Result(ResultType.Success);
    }

    @Override
    public Result deleteEntry(Integer id) {

        Entry entry = new Entry();

        entry.setId(id);

        List<Entry> allById = entryMapper.findAllById(entry);

        if(allById.size()>0){
            entryMapper.deleteEntryById(id);
            return new Result(ResultType.Success);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result updateEntry(Entry entry) {

        Group group = new Group();

        group.setId(entry.getGroupId());

        List<Group> allGroupById = groupMapper.findAllById(group);

        if(allGroupById.size()==0){
            return new Result(ResultType.NotFind);
        }

        if(entry.getJudgesId()!=null){
            Judges judges = new Judges();

            judges.setId(entry.getJudgesId());

            List<Judges> oneById = judgesMapper.findOneById(judges);

            if(oneById.size()==0){
                return new Result(ResultType.NotFind);
            }
        }

        List<Entry> allById = entryMapper.findAllById(entry);

        if(allById.size()>0){

            entryMapper.updateEntry(entry);

            if(entry.getFiles()!=null){
                fileService.updateEntryIdById(entry);
            }


            return new Result(ResultType.Success);
        }

        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findOneByGroupId(Group group) {
        List<Entry> entryListByGroupId = entryMapper.findEntryListByGroupId(group.getId());

        if(entryListByGroupId.size()>0){
            return new Result(ResultType.Success,entryListByGroupId);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findOneByJudgesId(Judges judges) {
        List<Entry> entryListByJudgesId = entryMapper.findEntryListByJudgesId(judges.getId());

        if(entryListByJudgesId.size()>0){
            return new Result(ResultType.Success,entryListByJudgesId);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findAll() {

        List<Entry> all = entryMapper.findAll();

        if(all.size()>0){
            return new Result(ResultType.Success,all);
        }

        return new Result(ResultType.EmptyData);
    }
}
