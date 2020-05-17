package com.fw.service;

import com.fw.domain.Admin;
import com.fw.domain.Result;

import java.util.List;

/**
 * @author yqf
 */
public interface AdminService {

    /**
     * 查找 admin by adminName
     * @param admin entity
     * @return admin
     * */
    Result findOneByName(Admin admin);

    /**
     * find admin by name and password
     * @param admin entity
     * @return Admin
     * */
    Result findOneByNameAndPassword(Admin admin);
}
