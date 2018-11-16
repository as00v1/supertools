package com.qiaohx.encrypt.controller;

import com.qiaohx.common.date.DateFormatRules;
import com.qiaohx.common.date.DateUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test() {
        return DateUtil.dateToStr(DateFormatRules.YYYY_MM_DD_HH_MM_SS);
    }
}
