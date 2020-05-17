package com.fw.controller;


import com.fw.domain.*;
import com.fw.service.AdminService;
import com.fw.service.GroupService;
import com.fw.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/login")
@ResponseBody
public class LoginController {

    @Autowired
    private GroupService groupService;


    @Autowired
    private AdminService adminService;



    @PostMapping
    public ResponseEntity<Result> loginCheck(@Validated @RequestBody User user){

        Result result = null;

        if(Role.Group.getType().equals(user.getRole())){
            Group group = new Group();

            group.setEmail(user.getAccount());

            group.setPassword(user.getPassword());

            result = groupService.findOneByEmailAndPassword(group);

        }

        if (Role.Admin.getType().equals(user.getRole())) {

            Admin admin = new Admin();

            admin.setAdminName(user.getAccount());

            admin.setPassword(user.getPassword());

            result = adminService.findOneByNameAndPassword(admin);
        }

        if(result==null){
            return new ResponseEntity<>(new Result(ResultType.UnRole,"角色错误"),HttpStatus.OK);
        }

        if(result.getCode()==0){

            Map<String,Object> data = new HashMap<>(4);

            data.put("token",TokenUtil.sign(user));

            data.put("role",user.getRole());

            result.setData(data);

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
