package com.qiaohx.encrypt.service.md5;

import com.qiaohx.encrypt.model.Emd5;

public interface IMd5Service {
    /**
     * 查找字符串对应的md5
     * @param value
     * @return
     */
    Emd5 selectByValue(String value) throws Exception;
}
