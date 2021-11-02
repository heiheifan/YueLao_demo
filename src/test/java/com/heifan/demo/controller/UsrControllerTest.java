package com.heifan.demo.controller;

import com.heifan.demo.DemoApplicationTests;
import com.heifan.demo.domain.param.UsrParam;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

@Slf4j
public class UsrControllerTest extends DemoApplicationTests {

    @Test
    public void login() throws Exception {
        UsrParam usrParam = new UsrParam();
        usrParam.setUsrName("HiF");
        usrParam.setPassword("123456");
        MvcResult mvcResult = executePost(usrParam, "/usr/login");
    }
}
