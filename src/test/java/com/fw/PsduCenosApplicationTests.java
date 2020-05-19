package com.fw;

import com.fw.domain.*;
import com.fw.mapper.*;
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
import java.util.List;

@SpringBootTest
class PsduCenosApplicationTests {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private EntryMapper entryMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private JudgesMapper judgesMapper;

    @Autowired
    private FileMapper fileMapper;


    @Test
    void contextLoads() {
        Admin admin = new Admin();

        admin.setId(2);
        admin.setAdminName("admin2");
        admin.setPassword("123456");

        adminMapper.updateAdmin(admin);

//        System.out.println(oneByNameAndPassword);
    }

    @Test
    void entryMapperTest() {
       // List<Entry> entryListByGroupId = entryMapper.findEntryListByGroupId(3);
        // System.out.println(entryListByGroupId);
        //List<Entry> listByJudgesId = entryMapper.findEntryListByJudgesId(2);

       // System.out.println(listByJudgesId);
        Entry entry = new Entry();

        entry.setEntryName("ok");
        entry.setGroupId(3);
        entry.setId(5);
        System.out.println(entry);
//        entryMapper.deleteEntryById(entry.getId());
    }

    @Test
    void groupMapperTest(){

//        Group group = new Group();
//        group.setEmail("k");
//        group.setPassword("kou");
//        group.setId(4);
//
//        groupMapper.updateGroup(group);
//        Group oneByEmailAndPassword = groupMapper.findOneByEmailAndPassword(group);
//        System.out.println(oneByEmailAndPassword);


//        List<Group> all = groupMapper.findAll();
//        System.out.println(all);
    }

    @Test
    public void judgesMapperTest(){
        Judges judges = new Judges();

        judges.setId(3);

        judges.setEmail("skk");

        judges.setPassword("s");

//        Judges oneByEmailAndPassword = judgesMapper.findOneByEmailAndPassword(judges);
//
//        System.out.println(oneByEmailAndPassword);

        judgesMapper.deleteJudges(judges.getId());


    }
    @Test
    void jwtTest(){
//        User user = new User();
//        user.setUserName("yqf");
//        user.setPassword("123");
//        String sign = TokenUtil.sign(user);
        System.out.println(TokenUtil.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsInVzZXJOYW1lIjoieXFmIiwiZXhwIjoxNTg5Njg2MDUxfQ.CA4_33P9w7m1Q0nC85V0cNnxt2RL77yKP_VkznAUKyU"));
    }

    @Test
    void testEnum(){
        Group group = new Group();

        GroupServiceImpl groupService = new GroupServiceImpl();

        Result result = groupService.addGroup(group);

        System.out.println(result);
    }

    @Test
    void testFile() throws IOException {
        String classPath = ResourceUtils.getURL("classpath:").getPath();

        File filePath = new File(classPath);

        Path uploadPath = Paths.get(
                filePath.getAbsolutePath() + "/uploads"
        );

        Files.createDirectories(uploadPath);
        System.out.println(uploadPath);
    }

    @Test
    void testFileMapper(){
        Entry entry = new Entry();

        entry.setId(7);

        UploadFile uploadFileById = fileMapper.findUploadFileById(6);

        Entry oneById = entryMapper.findOneById(entry);

        uploadFileById.setEntryId(entry.getId());

        List<UploadFile> files = oneById.getFiles();

        files.add(uploadFileById);

        for (UploadFile file : files) {
            fileMapper.updateEntryIdById(file);
        }


        System.out.println(files);
    }

}
