package com.fw.domain;

/**
 * 角色
 * @author yqf
 */

public enum Role {
    /**
     * 团队
     * */
    Group("group"),

    /**
     * 管理员
     * */
    Admin("admin"),

    /**
     * 评审员
     * */
    Judges("judges");


    private String type;

    Role(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
