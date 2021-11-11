package com.heifan.demo.controller;

import com.heifan.demo.domain.param.UsrParam;
import com.heifan.demo.service.UsrServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户
 *
 * @Author HiF
 * @Date 2021/10/27 14:09
 */
@RequestMapping("/usr")
@RestController
public class UsrController {

    @Autowired
    UsrServiceI usrService;
    /**
     *
     * @Param usrParam
	 * @Param request
	 * @Param response
     * @Return java.lang.Object
     * @Author (HiF)
     * @Date 2021/11/3 10:39
     */
    @RequestMapping("/login")
    public Object login(@RequestBody UsrParam usrParam,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        return usrService.login(usrParam, request, response);
    }
}
