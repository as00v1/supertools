package com.qiaohx.encrypt.service.md5;

import net.sf.json.JSONObject;

public interface IMd5Service {
    /**
     * 查找字符串对应的md5
     * @param value
     * @return
     */
    JSONObject selectByValue(String value) throws Exception;
}
