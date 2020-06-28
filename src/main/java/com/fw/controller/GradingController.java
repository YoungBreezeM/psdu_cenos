package com.fw.controller;

import com.fw.domain.Grading;
import com.fw.domain.Result;
import com.fw.domain.Rule;
import com.fw.service.GradingService;
import org.apache.ibatis.annotations.Delete;
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
@RequestMapping("/grading")
public class GradingController {

    @Autowired
    private GradingService gradingService;

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = gradingService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Result> findOneById(@PathVariable Integer id){
        Grading grading = new Grading();
        grading.setId(id);
        Result oneById = gradingService.findOneById(grading);
        return new ResponseEntity<>(oneById,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Result> addGrading(@Validated(Insert.class) @RequestBody Grading grading){
        Result result = gradingService.addGrading(grading);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateGrading(@Validated(Update.class) @RequestBody Grading grading){
        Result result = gradingService.updateGrading(grading);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteGrading(@PathVariable Integer id){
        Grading grading = new Grading();
        grading.setId(id);
        Result result = gradingService.deleteGrading(grading);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("rule/{id}")
    public ResponseEntity<Result> deleteRule(@PathVariable Integer id){
        Rule rule = new Rule();
        rule.setId(id);
        Result result = gradingService.deleteRule(rule);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
