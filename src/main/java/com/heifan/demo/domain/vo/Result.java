package com.heifan.demo.domain.vo;

import com.heifan.demo.domain.enums.ResultEnum;

public class Result {
    public static ResultVo success(Object object) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.setData(object);
        ResultVo.setCode(ResultEnum.SUCCESS.getCode());
        ResultVo.setMsg(ResultEnum.SUCCESS.getMessage());
        return ResultVo;
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.setCode(code);
        ResultVo.setMsg(msg);
        return ResultVo;
    }

    public static ResultVo error(ResultEnum resultEnum) {
        ResultVo ResultVo = new ResultVo();
        ResultVo.setCode(resultEnum.getCode());
        ResultVo.setMsg(resultEnum.getMessage());
        return ResultVo;
    }
}
