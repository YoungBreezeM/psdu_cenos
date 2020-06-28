package com.fw.controller;

import com.fw.domain.Group;
import com.fw.domain.Grouping;
import com.fw.domain.Result;
import com.fw.service.GroupingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/grouping")
public class GroupingController {

    @Autowired
    private GroupingService groupingService;

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = groupingService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Result> addRecord(@RequestBody Grouping grouping){
        Result result = groupingService.addRecord(grouping);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteRecord(@PathVariable Integer id){
        Result result = groupingService.deleteRecord(id);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateRecord(@RequestBody Grouping grouping){
        Result result = groupingService.updateRecord(grouping);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
