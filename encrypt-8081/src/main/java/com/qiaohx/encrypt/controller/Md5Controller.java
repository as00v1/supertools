package com.qiaohx.encrypt.controller;

import com.qiaohx.encrypt.service.md5.IMd5Service;
import com.qiaohx.utils.constant.ConstantCode;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/md5")
public class Md5Controller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private IMd5Service iMd5Service;

    @RequestMapping(value = "/selectByValue/{version}", method = RequestMethod.POST)
    public String selectByValue(@RequestBody String str, @PathVariable String version){
        logger.info("version : " + version);
        JSONObject resultJson = new JSONObject();
        try {
            JSONObject jsonObject = JSONObject.fromObject(str);
            String value = jsonObject.containsKey("value") ? jsonObject.getString("value") : "";
            JSONObject md5Res = iMd5Service.selectByValue(value);
            resultJson.put(ConstantCode.CODE, ConstantCode.CODE_200);
            resultJson.put(ConstantCode.DATA, md5Res);
        }catch (Exception e){
            logger.error("MD5异常", e.getMessage(), e);
            e.printStackTrace();
            resultJson.put(ConstantCode.CODE, ConstantCode.CODE_500);
            resultJson.put(ConstantCode.MESSAGE, ConstantCode.MESSAGE_500);
        }
        return resultJson.toString();
    }
}
