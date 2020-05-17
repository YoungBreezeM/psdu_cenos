package com.fw.controller;

import com.fw.domain.Group;
import com.fw.domain.Result;
import com.fw.service.GroupService;
import org.apache.catalina.loader.ResourceEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/registry")
public class RegistryController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Result> registryAccount(@Validated @RequestBody Group group){
        System.out.println(group);

        Result result = groupService.addGroup(group);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
