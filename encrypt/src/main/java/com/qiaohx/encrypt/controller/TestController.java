package com.qiaohx.encrypt.controller;

import com.qiaohx.utils.date.DateFormatRules;
import com.qiaohx.utils.date.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public String test() {
        logger.trace("trace log");
        logger.debug("debug log");
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");
        try {
            new BigDecimal("a");
        }catch (Exception e){
            // 记录异常的方法
            logger.error("异常", e.getMessage(), e);
            e.printStackTrace();
        }
        return DateUtil.dateToStr(DateFormatRules.YYYY_MM_DD_HH_MM_SS);
    }
}
