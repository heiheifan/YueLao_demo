package com.heifan.demo.service;

import com.heifan.demo.domain.param.UsrParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @Author HiF
 * @Date 2021/10/27 14:11
 */
public interface UsrServiceI {

    /**
     * 登录
     * @Param usrParam
	 * @Param request
	 * @Param response
     * @Return java.lang.Object
     * @Author HiF
     * @Date 2021/10/27 14:47
     */
    Object login(UsrParam usrParam,
                 HttpServletRequest request,
                 HttpServletResponse response);
}
