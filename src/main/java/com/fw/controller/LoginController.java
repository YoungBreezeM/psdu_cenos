package com.fw.controller;


import com.fw.domain.*;
import com.fw.service.AdminService;
import com.fw.service.GroupService;
import com.fw.service.JudgesService;
import com.fw.utils.TokenUtil;
import org.apache.ibatis.annotations.Select;
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

    @Autowired
    private JudgesService judgesService;


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

        if(Role.Judges.getType().equals(user.getRole())){

            Judges judges = new Judges();

            judges.setEmail(user.getAccount());

            judges.setPassword(user.getPassword());

            result = judgesService.findOneByEmailAndPassword(judges);
        }

        if(result==null){
            return new ResponseEntity<>(new Result(ResultType.UnRole),HttpStatus.OK);
        }

        Object data = result.getData();

        if(data!=null){
            Map<String,Object> rs = new HashMap<>(10);
            rs.put("token",TokenUtil.sign(user));
            rs.put("user",data);
            result.setData(rs);
        }

        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
