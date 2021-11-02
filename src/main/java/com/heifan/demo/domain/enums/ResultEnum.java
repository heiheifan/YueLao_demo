package com.heifan.demo.domain.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    SUCCESS(200, "成功"),

    FAILURE(-1,"系统异常"),

    PARAM_ERROR(201, "参数不正确"),

    RESULT_NOT_EXIST(301, "查询结果不存在");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
