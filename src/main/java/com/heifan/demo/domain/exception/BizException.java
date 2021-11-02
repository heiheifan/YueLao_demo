package com.heifan.demo.domain.exception;

import com.heifan.demo.domain.enums.ResultEnum;
/**
 * 自定义异常
 * @Author HiF
 * @Date 2021/10/27 15:22
 */
public class BizException extends RuntimeException{
    private Integer code;

    public BizException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
