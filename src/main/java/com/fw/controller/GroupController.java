package com.fw.controller;

import com.fw.domain.Group;
import com.fw.domain.GroupBatch;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.mapper.GroupMapper;
import com.fw.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Result> updateInfo(@Validated @RequestBody Group group){

        Result result = groupService.updateGroupInfo(group);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Result> deleteGroup(@PathVariable Integer id){
        Group group = new Group();
        group.setId(id);

        Result result = groupService.deleteById(group);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Result> findAll(){
        Result all = groupService.findAll();

        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping("/batchUpdate")
    public ResponseEntity<Result> batchUpdate(@RequestBody GroupBatch groupBatch){
        List<Group> groups = groupBatch.getGroups();
        if(groups.size()>0){
            for (Group group : groups) {
                groupService.updateGroupInfo(group);
            }
            Result result = new Result(ResultType.UpdateSuccess);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>(new Result(ResultType.NotFind),HttpStatus.OK);
    }

}
