package com.fw.controller;


import com.fw.domain.Group;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author yqf
 */

@RestController
public class TestController {

    @GetMapping("/")
    public Group test(){

        Group group = new Group();

        group.setId(1);

        group.setEmail("s");

        group.setPassword("1238");
        System.out.println(group);

        return group;
    }

}
