package com.qiaohx.encrypt.mapper;

import com.qiaohx.encrypt.model.Emd5;

public interface Emd5Mapper {
    int deleteByPrimaryKey(String md5Key);

    int insert(Emd5 record);

    int insertSelective(Emd5 record);

    Emd5 selectByPrimaryKey(String md5Key);

    int updateByPrimaryKeySelective(Emd5 record);

    int updateByPrimaryKey(Emd5 record);
}