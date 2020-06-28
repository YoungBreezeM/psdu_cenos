package com.fw.controller;

import com.fw.domain.*;
import com.fw.service.EntryService;
import com.fw.utils.DateUtil;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


/**
 * @author yqf
 */
@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    private EntryService entryService;

    @PutMapping
    public ResponseEntity<Result> addEntry(@Validated(Insert.class) @RequestBody Entry entry){

        System.out.println(entry);
        entry.setCreatedTime(new Date(System.currentTimeMillis()));

        Result result = entryService.addEntry(entry);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/entryJudges")
    public ResponseEntity<Result> addEntryJudges(@RequestBody Entry entry){
        List<EntryJudges> entryJudges = entry.getEntryJudges();
        for (EntryJudges entryJudge : entryJudges) {
            entryService.addEntryJudges(entryJudge);
        }

        return new ResponseEntity<>(new Result(ResultType.AddSuccess),HttpStatus.OK);
    }

    @PostMapping("/entryJudges")
    public ResponseEntity<Result> updateRemark(@RequestBody EntryJudges entryJudges){
        Result result = entryService.updateRemarkAndScore(entryJudges);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteEntry(@PathVariable Integer id){
        Result result = entryService.deleteEntry(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/entryJudges/{id}")
    public ResponseEntity<Result> deleteEntryJudges(@PathVariable Integer id){
        EntryJudges entryJudges = new EntryJudges();
        entryJudges.setEntryId(id);
        Result result = entryService.deleteEntryJudges(entryJudges);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("/entryJudges")
    public ResponseEntity<Result> deleteByEntryAndJudges(@Validated(Delete.class) @RequestBody EntryJudges entryJudges){
        Result result = entryService.deleteByEntryIdAndJudges(entryJudges);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/entryJudges/{entryId}/{judgesId}")
    public ResponseEntity<Result> findReMark(@PathVariable Integer entryId, @PathVariable Integer judgesId){
        EntryJudges entryJudges = new EntryJudges();
        entryJudges.setEntryId(entryId);
        entryJudges.setJudgesId(judgesId);
        Result remark = entryService.findRemark(entryJudges);
        return new ResponseEntity<>(remark,HttpStatus.OK);
    }

    @GetMapping("/entryJudges/{entryId}")
    public ResponseEntity<Result> findRemarkList(@PathVariable Integer entryId){
        Entry entry = new Entry();
        entry.setId(entryId);
        Result remarkList = entryService.findAllByEntryId(entry);
        return new ResponseEntity<>(remarkList,HttpStatus.OK);
    }

    @GetMapping("/entryScore/{entryJudgesId}")
    public ResponseEntity<Result> findEntryScore(@PathVariable Integer entryJudgesId){
        EntryScore entryScore = new EntryScore();
        entryScore.setEntryJudgesId(entryJudgesId);
        Result allByEntryJudgesId = entryService.findAllByEntryJudgesId(entryScore);
        return new ResponseEntity<>(allByEntryJudgesId,HttpStatus.OK);
    }



    @PostMapping
    public ResponseEntity<Result> updateEntry(@Validated(Update.class) @RequestBody Entry entry){
        Result result = entryService.updateEntry(entry);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("/group/{id}")
    public ResponseEntity<Result> findEntryByGroupId(@PathVariable Integer id){
        Group group = new Group();
        group.setId(id);
        Result oneByGroupId = entryService.findOneByGroupId(group);
        return new ResponseEntity<>(oneByGroupId,HttpStatus.OK);
    }

    @GetMapping("/judges/{id}")
    public ResponseEntity<Result> findEntryByJudgesId(@PathVariable Integer id){
        EntryJudges entryJudges = new EntryJudges();
        entryJudges.setJudgesId(id);
        Result oneByJudgesId = entryService.findAllByJudgesId(entryJudges);
        return new ResponseEntity<>(oneByJudgesId,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = entryService.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Result> getEntityById(@PathVariable Integer id){
        Entry entry = new Entry();
        entry.setId(id);
        Result oneById = entryService.findOneById(entry);

        return new ResponseEntity<>(oneById,HttpStatus.OK);
    }
}
