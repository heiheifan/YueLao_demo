package com.heifan.demo;

import com.google.gson.Gson;
import com.heifan.demo.utils.JsonTool;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DemoApplicationTests {

    @Autowired
    protected MockMvc mockMvc;

    public static final String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Autowired(required = false)
    protected JdbcTemplate jdbcTemplate;

    protected MvcResult executePost(Object object, String path) throws Exception {
        log.info("param {}", JsonTool.beanToJson(object));
        return mockMvc.perform(MockMvcRequestBuilders
                .post(path)
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(new Gson().toJson(object))
                // 设置返回值类型为utf-8，否则默认为ISO-8859-1
                .accept(APPLICATION_JSON_UTF8_VALUE)
//                .header(HttpHeaders.AUTHORIZATION, token)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
                .andExpect(jsonPath("$.code").value(200))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
