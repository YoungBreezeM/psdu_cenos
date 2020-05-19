package com.fw.controller;

import com.fw.domain.Entry;
import com.fw.domain.Group;
import com.fw.domain.Judges;
import com.fw.domain.Result;
import com.fw.service.EntryService;
import com.fw.utils.DateUtil;

import org.apache.ibatis.annotations.Insert;

import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
        entry.setCreatedTime(new DateUtil().createTime());

        Result result = entryService.addEntry(entry);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteEntry(@PathVariable Integer id){
        Result result = entryService.deleteEntry(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
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
        Judges judges = new Judges();
        judges.setId(id);
        Result oneByJudgesId = entryService.findOneByJudgesId(judges);
        return new ResponseEntity<>(oneByJudgesId,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = entryService.findAll();
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
}
