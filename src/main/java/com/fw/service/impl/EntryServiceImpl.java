package com.fw.service.impl;

import com.fw.domain.*;
import com.fw.mapper.*;
import com.fw.service.EntryService;
import com.fw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    private EntryJudgesMapper entryJudgesMapper;

    @Autowired
    private FileServiceImpl fileService;

    @Autowired
    private EntryScoreMapper entryScoreMapper;

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
    public Result findAllByJudgesId(EntryJudges entryJudges) {
        List<EntryJudges> allByJudgesId = entryJudgesMapper.findAllEntryByJudgesId(entryJudges);

        if (allByJudgesId.size()>0){
            List<Entry> entries = new ArrayList<>();
            for (EntryJudges judges : allByJudgesId) {
                Entry entry = judges.getEntry();
                List<Grading> gradings = entry.getGradings();
                if(gradings.size()>0){
                    for (Grading grading : gradings) {
                        EntryScore entryScore = new EntryScore();
                        entryScore.setGradingId(grading.getId());
                        entryScore.setEntryJudgesId(judges.getId());
                        EntryScore score = entryScoreMapper.findOneByGradingIdAndEntryJudgesId(entryScore);
                        if(score!=null){
                            entry.setIsScore(true);
                        }else {
                            entry.setIsScore(false);
                        }
                    }
                }
                entries.add(entry);
            }
            return new Result(ResultType.Success,entries);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findAll() {

        List<Entry> all = entryMapper.findAll();

        if(all.size()>0){
            for (Entry entry : all) {
                List<EntryScore> entryAllScore = this.getEntryAllScore(entry);
                if (entryAllScore!=null){
                    Double allScore =0.00;
                    for (EntryScore entryScore : entryAllScore) {
                        allScore +=(double)entryScore.getScore();
                    }
                    Double size = (double)entry.getEntryJudges().size();
                    DecimalFormat df = new DecimalFormat("0.00");
                    String num = df.format(allScore/size);
                    entry.setAveScore(num);
                }

            }
            return new Result(ResultType.Success,all);
        }

        return new Result(ResultType.EmptyData);
    }

    @Override
    public Result findOneById(Entry entry) {
        Entry oneById = entryMapper.findOneById(entry);

        if(oneById!=null){
            return new Result(ResultType.Success,oneById);
        }
        return new Result(ResultType.EmptyData);
    }

    @Override
    public Result addEntryJudges(EntryJudges entryJudges) {

        entryJudgesMapper.addEntryJudges(entryJudges);

        return new Result(ResultType.AddSuccess);
    }

    @Override
    public Result deleteEntryJudges(EntryJudges entryJudges) {
        entryJudgesMapper.deleteByEntryId(entryJudges);
        return new Result(ResultType.DeleteSuccess);
    }

    @Override
    public Result deleteByEntryIdAndJudges(EntryJudges entryJudges) {
        entryJudgesMapper.deleteByEntryIdAndJudgesId(entryJudges);
        return new Result(ResultType.DeleteSuccess);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result updateRemarkAndScore(EntryJudges entryJudges) {
        entryJudgesMapper.updateRemark(entryJudges);
        EntryJudges oneByEntryIdAndJudgesId = entryJudgesMapper.findOneByEntryIdAndJudgesId(entryJudges);

        List<Grading> gradings = entryJudges.getGradings();

        EntryScore entryScore = new EntryScore();
        entryScore.setEntryJudgesId(oneByEntryIdAndJudgesId.getId());
        for (Grading grading : gradings) {

            entryScore.setGradingId(grading.getId());
            entryScore.setScore(grading.getScore());

            EntryScore oneByGradingIdAndEntryJudgesId = entryScoreMapper.findOneByGradingIdAndEntryJudgesId(entryScore);

            if(oneByGradingIdAndEntryJudgesId!=null){
                entryScoreMapper.updateScore(entryScore);
            }else {
                entryScoreMapper.addEntryScore(entryScore);
            }

        }

        return new Result(ResultType.UpdateSuccess);
    }

    @Override
    public Result findRemark(EntryJudges entryJudges) {
        EntryJudges oneByEntryIdAndJudgesId = entryJudgesMapper.findOneByEntryIdAndJudgesId(entryJudges);
        if(oneByEntryIdAndJudgesId!=null){
            Entry entry = oneByEntryIdAndJudgesId.getEntry();
            if(entry!=null){
                List<Grading> gradings = entry.getGradings();
                if(gradings.size()>0){
                    for (Grading grading : gradings) {
                        EntryScore entryScore = new EntryScore();
                        entryScore.setGradingId(grading.getId());
                        entryScore.setEntryJudgesId(oneByEntryIdAndJudgesId.getId());
                        EntryScore score = entryScoreMapper.findOneByGradingIdAndEntryJudgesId(entryScore);
                        if(score!=null){
                            grading.setScore(score.getScore());
                        }

                    }
                }
            }
            return new Result(ResultType.Success,oneByEntryIdAndJudgesId);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findAllByEntryJudgesId(EntryScore entryScore) {
        List<EntryScore> allByEntryJudgesId = entryScoreMapper.findAllByEntryJudgesId(entryScore);
        if(allByEntryJudgesId.size()>0){
            return new Result(ResultType.Success,allByEntryJudgesId);
        }
        return new Result(ResultType.NotFind);
    }

    @Override
    public Result findAllByEntryId(Entry entry) {
        List<EntryJudges> allByEntryId = entryJudgesMapper.findAllByEntryId(entry.getId());
        if(allByEntryId.size()>0){
            return new Result(ResultType.Success,allByEntryId);
        }
        return new Result(ResultType.NotFind);
    }

    private List<EntryScore> getEntryAllScore(Entry entry) {

        List<EntryJudges> allByEntryId = entryJudgesMapper.findAllByEntryId(entry.getId());
        if(allByEntryId.size()>0){
            List<EntryScore> entryScores = new ArrayList<>();
            for (EntryJudges judges : allByEntryId) {
                List<EntryScore> entryScores1 = judges.getEntryScores();
                if (entryScores1.size()>0){
                    entryScores.addAll(entryScores1);
                }
            }
            return entryScores;
        }
        return null;
    }

}
