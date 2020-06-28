package com.fw.controller;

import com.fw.domain.Group;
import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.service.GroupService;
import org.apache.catalina.loader.ResourceEntry;
import org.apache.ibatis.annotations.Insert;
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
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private GroupService groupService;

    @PutMapping
    public ResponseEntity<Result> registryAccount(@Validated(Insert.class) @RequestBody Group group){
        System.out.println(group);

        Result result = groupService.addGroup(group);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @GetMapping("{groupName}")
    public ResponseEntity<Result> findAllByName(@PathVariable String groupName){
        Group group = new Group();
        group.setGroupName(groupName);

        List<Group> oneByName = groupService.findOneByName(group);

        if(oneByName!=null){
            return new ResponseEntity<>(new Result(ResultType.GroupNameHasRegistered),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success),HttpStatus.OK);
    }
}
