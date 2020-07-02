package com.fw.service;

import com.fw.domain.SysMail;

/**
 * @author yqf
 */
public interface SysMailService {

    /**
     * get one mail
     * @return entity
     * */
    SysMail findOne();

    /**
     * update mail config
     * @param sysMail entity
     * */
    void updateMail(SysMail sysMail);
}
