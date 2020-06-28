package com.fw.controller;

import com.fw.domain.Judges;
import com.fw.domain.Result;
import com.fw.service.JudgesService;
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
@RequestMapping("/judges")
public class JudgesController {

    @Autowired
    private JudgesService judgesService;

    @PutMapping
    public ResponseEntity<Result> addJudges(@Validated(Insert.class) @RequestBody Judges judges){

        Result result = judgesService.addRecord(judges);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteJudges(@PathVariable Integer id){
        Judges judges = new Judges();
        judges.setId(id);
        Result result = judgesService.deleteById(judges);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateJudges(@Validated(Update.class) @RequestBody Judges judges){

        Result result = judgesService.updateRecord(judges);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result> findAll(){

        Result all = judgesService.findAll();

        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Result> findOneById(@PathVariable Integer id){
        Judges judges = new Judges();
        judges.setId(id);
        Result oneById = judgesService.findOneById(judges);
        return new ResponseEntity<>(oneById,HttpStatus.OK);
    }
}
