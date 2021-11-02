package com.heifan.demo.manager.impl;

import com.heifan.demo.dao.UsrInfoDao;
import com.heifan.demo.domain.enums.ResultEnum;
import com.heifan.demo.domain.exception.BizException;
import com.heifan.demo.domain.param.UsrParam;
import com.heifan.demo.manager.UsrManagerI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Service
@Slf4j
public class UsrManagerImpl implements UsrManagerI {

    @Resource
    UsrInfoDao usrInfoDao;

    @Override
    public Object login(UsrParam usrParam) {
        String usrName = usrParam.getUsrName();
        String password = usrParam.getPassword();
        String res = usrInfoDao.login(usrName);
        if (!Objects.equals(password, res)) {
            throw new BizException(ResultEnum.RESULT_NOT_EXIST.getCode(),"账号密码出错！");
        }
        return 1;
    }
}
