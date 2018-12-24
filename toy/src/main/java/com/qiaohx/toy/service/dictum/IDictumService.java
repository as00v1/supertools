package com.qiaohx.toy.service.dictum;

import net.sf.json.JSONObject;

/**
 * 诗词名言
 * @author jiayong
 * @since 2018年12月11日13:49:06
 */
public interface IDictumService {

    /**
     * 新增一条
     * @param jsonObject
     * @return
     * @throws Exception
     */
    JSONObject insertDictum(JSONObject jsonObject) throws Exception;
}
