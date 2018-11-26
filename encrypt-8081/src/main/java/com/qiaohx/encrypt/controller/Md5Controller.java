package com.qiaohx.encrypt.controller;

import com.qiaohx.encrypt.model.Emd5;
import com.qiaohx.encrypt.service.md5.IMd5Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Md5Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IMd5Service iMd5Service;

    @RequestMapping("/selectByValue")
    public Emd5 selectByValue(@RequestBody String str){
        Emd5 emd5 = null;
        try {
            emd5 = iMd5Service.selectByValue(str);
        }catch (Exception e){
            logger.error("MD5异常", e.getMessage(), e);
            e.printStackTrace();
        }
        return emd5;
    }
}
