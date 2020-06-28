package com.fw.controller;

import com.fw.domain.Result;
import com.fw.domain.Track;
import com.fw.service.TrackService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = trackService.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Result> addRecord(@RequestBody Track track){
        Result result = trackService.addRecord(track);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteRecord(@PathVariable Integer id){
        Result result = trackService.deleteRecord(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Result> updateRecord(@RequestBody Track track){
        Result result = trackService.updateRecord(track);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
