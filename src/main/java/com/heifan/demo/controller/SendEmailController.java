package com.heifan.demo.controller;

import com.heifan.demo.domain.enums.ResultEnum;
import com.heifan.demo.domain.vo.Result;
import com.heifan.demo.utils.SendEmail;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class SendEmailController {

    @RequestMapping("/send")
    public Object sendEmail() throws Exception {
        SendEmail.send();
        return Result.success(ResultEnum.SUCCESS.getMessage());
    }
}
