package com.heifan.demo.service.impl;

import com.heifan.demo.domain.enums.ResultEnum;
import com.heifan.demo.domain.exception.BizException;
import com.heifan.demo.domain.param.UsrParam;
import com.heifan.demo.domain.vo.Result;
import com.heifan.demo.manager.UsrManagerI;
import com.heifan.demo.service.UsrServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class UsrServiceImpl implements UsrServiceI {

    @Autowired
    UsrManagerI usrManager;

    @Override
    public Object login(UsrParam usrParam,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String usrName = usrParam.getUsrName();
        String password = usrParam.getPassword();
        if (null == usrName || null == password) {
            log.error("请求参数异常！usrName:{},password:{}", usrName, password);
            throw new BizException(ResultEnum.PARAM_ERROR.getCode(), "请求参数异常！");
        }
        Object login = usrManager.login(usrParam);
        return Result.success(login);
    }
}
