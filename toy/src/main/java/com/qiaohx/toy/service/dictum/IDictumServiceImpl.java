package com.qiaohx.toy.service.dictum;

import com.qiaohx.toy.mapper.TDictumInfoMapper;
import com.qiaohx.toy.model.TDictumInfo;
import com.qiaohx.toy.service.base.BaseService;
import com.qiaohx.utils.constant.ConstantCode;
import com.qiaohx.utils.sequence.SequenceUtil;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class IDictumServiceImpl extends BaseService implements IDictumService{

    @Resource
    private TDictumInfoMapper tDictumInfoMapper;

    @Override
    public JSONObject insertDictum(JSONObject jsonObject) throws Exception {
        JSONObject resultJson = new JSONObject();
        String author = jsonObject.containsKey("author") ? jsonObject.getString("author") : null;
        String title = jsonObject.containsKey("title") ? jsonObject.getString("title") : null;
        String content = jsonObject.containsKey("content") ? jsonObject.getString("content") : null;
        String dictumType = jsonObject.containsKey("dictumType") ? jsonObject.getString("dictumType") : null;
        String country = jsonObject.containsKey("country") ? jsonObject.getString("country") : null;

        TDictumInfo tDictumInfo = new TDictumInfo();
        tDictumInfo.setId(SequenceUtil.getSeq());
        tDictumInfo.setAuthor(author);
        tDictumInfo.setTitle(title);
        tDictumInfo.setContent(content);
        tDictumInfo.setDictumType(dictumType);
        tDictumInfo.setCountry(country);
        tDictumInfo.setCreateDate(new Date());
        int row = tDictumInfoMapper.insert(tDictumInfo);
        logger.info("影响行数：" + row);
        resultJson.put(ConstantCode.ERROR_CODE, ConstantCode.ERROR_CODE_0);
        return resultJson;
    }
}
