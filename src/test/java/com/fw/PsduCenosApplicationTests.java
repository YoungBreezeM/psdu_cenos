package com.fw;

import com.fw.domain.*;
import com.fw.mapper.*;
import com.fw.service.EntryService;
import com.fw.service.GradingService;

import com.fw.utils.MailUtil;
import com.fw.utils.TemplatesUtils;
import org.junit.jupiter.api.Test;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.mail.MessagingException;

import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class PsduCenosApplicationTests {


    @Autowired
    private GradingService gradingService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private EntryJudgesMapper entryJudgesMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private TrackMapper trackMapper;

    @Autowired
    private TrackGroupingMapper trackGroupingMapper;

    @Autowired
    private GroupingMapper groupingMapper;

    @Autowired
    private GradingGroupingMapper gradingGroupingMapper;

    @Autowired
    private SysMailMapper sysMailMapper;

    @Autowired
    private TemplatesUtils templatesUtils;

    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;



    @Test
    void testGradingService(){
        Grading grading = new Grading();
        grading.setScoreName("长相");
        grading.setValue(50);
        grading.setId(14);

        Rule rule = new Rule();
        rule.setRuleContent("hi ywj");
        rule.setGradingId(grading.getId());

        List<Rule> rules = new ArrayList<>();
        rules.add(rule);

        grading.setRules(rules);

        gradingService.deleteGrading(grading);
    }

    @Test
    void testEntryJudges(){
        EntryJudges entryJudges = new EntryJudges();
        entryJudges.setJudgesId(7);
        entryJudges.setEntryId(14);
        entryJudges.setRemark("ok");

        List<EntryJudges> allByEntryId = entryJudgesMapper.findAllByEntryId(14);
        if(allByEntryId.size()>0){
            List<EntryScore> entryScores = new ArrayList<>();
            for (EntryJudges judges : allByEntryId) {
                List<EntryScore> entryScores1 = judges.getEntryScores();
                if (entryScores1.size()>0){
                    entryScores.addAll(entryScores1);
                }
            }
            System.out.println(entryScores);
        }

    }

    @Test
    void spaceName(){
       Integer a = 69+61+70;
       int c =3;
       Double b ;
        DecimalFormat df = new DecimalFormat("0.00");//格式化小数
        String num = df.format((double) a/(double) c);//返回的是String类型
        System.out.println(num);
    }

//    @Test
//    void emailTest() throws  MessagingException {
//        String html = templatesUtils.renderCaptcha((int) ((Math.random()*9+1)*100000));
//        mailUtil.send("940695836@qq.com","940695836@qq.com","注册验证",html);
//
//    }

    @Test
    void redisTestSet(){
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        valueOperations.set("name","yqff");

        redisTemplate.expire("name",60, TimeUnit.SECONDS);

    }

    @Test
    void redisGet(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        /*从redis中获取验证码*/
        String verificationCode = (String) valueOperations.get("name");
        System.out.println(verificationCode);

    }
}
