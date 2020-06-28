package com.fw.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fw.domain.*;
import com.fw.mapper.AdminMapper;
import com.fw.mapper.GroupMapper;
import com.fw.mapper.JudgesMapper;
import com.fw.service.AdminService;
import com.fw.service.GroupService;
import com.fw.service.JudgesService;
import com.fw.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private JudgesMapper judgesMapper;

    @Autowired
    private GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<Result> resolveToken(HttpServletRequest request) throws JsonProcessingException {

        String token = request.getHeader("token");
        if (token!=null){
            User verify = TokenUtil.verify(token);
            Map<String,Object> map = new HashMap(10);
            assert verify != null;
            if(Role.Admin.getType().equals(verify.getRole())){
                Admin admin = new Admin();
                admin.setAdminName(verify.getAccount());
                admin.setPassword(verify.getPassword());
                map.put("info",adminMapper.findOneByNameAndPassword(admin));
            }
            if (Role.Judges.getType().equals(verify.getRole())){
                Judges judges = new Judges();
                judges.setEmail(verify.getAccount());
                judges.setPassword(verify.getPassword());
                map.put("info",judgesMapper.findOneByEmailAndPassword(judges));
            }
            if (Role.Group.getType().equals(verify.getRole())){
                Group group = new Group();
                group.setEmail(verify.getAccount());
                group.setPassword(verify.getPassword());
                map.put("info",groupMapper.findOneByEmailAndPassword(group));
            }
            map.put("role",verify.getRole());
            return new ResponseEntity<>(new Result(ResultType.ResolveSuccess,map),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.NotFind),HttpStatus.OK);
    }
}
