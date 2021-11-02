package com.heifan.demo.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {

    private static final long serialVersionUID = 3009132526980391812L;
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;

}
