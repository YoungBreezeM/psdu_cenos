package com.fw;

import com.fw.domain.*;
import com.fw.mapper.*;
import com.fw.service.EntryService;
import com.fw.service.GradingService;
import com.fw.service.impl.GroupServiceImpl;
import com.fw.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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




    @Test
    void testGradingService(){
        Grading grading = new Grading();
        grading.setScoreName("长相");
        grading.setValue(50);
        grading.setId(14);

        Rule rule = new Rule();
        rule.setRuleContent("hi siri ");
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

}
