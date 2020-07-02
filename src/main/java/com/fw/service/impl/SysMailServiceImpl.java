package com.fw.service.impl;

import com.fw.domain.SysMail;
import com.fw.mapper.SysMailMapper;
import com.fw.service.SysMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yqf
 */
@Service
public class SysMailServiceImpl implements SysMailService {

    @Autowired
    private SysMailMapper sysMailMapper;

    @Override
    public SysMail findOne() {

        return sysMailMapper.findOne();
    }

    @Override
    public void updateMail(SysMail sysMail) {
        sysMailMapper.updateMail(sysMail);
    }
}
