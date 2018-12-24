package com.qiaohx.toy.controller;

import com.qiaohx.toy.service.base.BaseService;
import com.qiaohx.toy.service.dictum.IDictumService;
import com.qiaohx.utils.check.CheckUtil;
import com.qiaohx.utils.constant.ConstantCode;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("dictum")
public class DictumController extends BaseService {

    @Resource
    private IDictumService iDictumService;

    @RequestMapping(value = "/addDictum/{version}", method = RequestMethod.POST)
    public String addDictum(@RequestBody String str, @PathVariable String version){
        JSONObject resultJson = new JSONObject();
        try {
            JSONObject jsonObject = JSONObject.fromObject(str);
            JSONObject checkJson = new JSONObject();
            checkJson.put("", "");
            JSONObject checkRes = CheckUtil.checkRequest(jsonObject, checkJson);
            if (!CheckUtil.checkResponse(checkRes)){
                resultJson.put(ConstantCode.CODE, ConstantCode.CODE_200);
                resultJson.put(ConstantCode.DATA, checkRes);
                return resultJson.toString();
            }
            JSONObject res = iDictumService.insertDictum(jsonObject);
            resultJson.put(ConstantCode.CODE, ConstantCode.CODE_200);
            resultJson.put(ConstantCode.DATA, res);
        }catch (Exception e){
            logger.error("MD5异常", e.getMessage(), e);
            e.printStackTrace();
            resultJson.put(ConstantCode.CODE, ConstantCode.CODE_500);
            resultJson.put(ConstantCode.MESSAGE, ConstantCode.MESSAGE_500);
        }
        return resultJson.toString();
    }
}
