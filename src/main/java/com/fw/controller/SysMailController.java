package com.fw.controller;

import com.fw.domain.Result;
import com.fw.domain.ResultType;
import com.fw.domain.SysMail;
import com.fw.service.SysMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yqf
 */

@RestController
@RequestMapping("/mail")
public class SysMailController {

    @Autowired
    private SysMailService sysMailService;

    @GetMapping
    ResponseEntity<Result> getMail(){
        SysMail one = sysMailService.findOne();

        if(one!=null){
            return new ResponseEntity<>(new Result(ResultType.Success,one), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
        }
    }

    @PostMapping
    ResponseEntity<Result> updateMailConfig(@Validated @RequestBody SysMail sysMail){
        sysMailService.updateMail(sysMail);
        return new ResponseEntity<>(new Result(ResultType.UpdateSuccess),HttpStatus.OK);
    }
}
