package com.fw.controller;

import com.fw.domain.*;
import com.fw.mapper.SysMailMapper;
import com.fw.service.GroupService;
import com.fw.utils.MailUtil;
import com.fw.utils.TemplatesUtils;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author yqf
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private SysMailMapper sysMailMapper;

    @Autowired
    private TemplatesUtils templatesUtils;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MailUtil mailUtil;

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

    @PostMapping("/checkMail")
    public ResponseEntity<Result> findAllByMail(@RequestBody User user){
        Group group = new Group();
        group.setEmail(user.getAccount());

        List<Group> oneByName = groupService.findAllByEmail(group);

        System.out.println(oneByName);
        if(oneByName.size()>0){
            return new ResponseEntity<>(new Result(ResultType.EmailHasRegistered),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.Success),HttpStatus.OK);
    }

    @PostMapping("/getCaptcha")
    public ResponseEntity<Result> getCaptcha(@RequestBody User user) throws MessagingException {
        SysMail sysMail = sysMailMapper.findOne();

        if(sysMail!=null){
            Integer code = (int) ((Math.random()*9+1)*100000);

            String html = templatesUtils.renderCaptcha(code);

            mailUtil.send(sysMail.getUserName(),user.getAccount(),"注册验证",html);
            ValueOperations<String, Integer> valueOperations = redisTemplate.opsForValue();
            valueOperations.set(user.getAccount(),code);


            return new ResponseEntity<>(new Result(ResultType.Success),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.EmptyData),HttpStatus.OK);
    }

    @PostMapping("/checkCaptcha/{captcha}")
    public ResponseEntity<Result> checkCaptcha(@RequestBody User user, @PathVariable Integer captcha){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        Integer code = (Integer) valueOperations.get(user.getAccount());

        if(code ==null){
            return new ResponseEntity<>(new Result(ResultType.CheckCaptchaPassTime),HttpStatus.OK);
        }

        if(!code.equals(captcha)){
            return new ResponseEntity<>(new Result(ResultType.CheckFail),HttpStatus.OK);
        }

        return new ResponseEntity<>(new Result(ResultType.CheckSuccess),HttpStatus.OK);
    }
}
