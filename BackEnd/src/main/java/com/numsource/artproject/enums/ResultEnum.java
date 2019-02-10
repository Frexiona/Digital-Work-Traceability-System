package com.numsource.artproject.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {
    PARAM_ERROR(0,"参数错误"),
    NOT_EXIST(1,"用户不存在"),
    OPERATE_ERROR(2,"操作错误"),
    ADDRESS_WRONG(3,"邮箱地址不是有效地址"),
    PASSWORD_WRONG(4,"密码错误"),
    EMAIL_REGISTED(5,"邮箱已被注册"),
    SEND_EMAIL_FAIL(6,"邮件发送失败"),
    CREATE_USER_ADDRESS_FAIL(7,"系统错误,生成用户唯一标识失败"),
    SYSTEM_ERROR(500,"系统错误"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
