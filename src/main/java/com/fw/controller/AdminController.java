package com.fw.controller;

import com.fw.domain.Admin;
import com.fw.domain.Result;
import com.fw.service.AdminService;
import org.apache.ibatis.annotations.Update;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping
    public ResponseEntity<Result> updateInfo(@Validated({Update.class}) @RequestBody Admin admin){

        Result result = adminService.updateAdminInfo(admin);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
