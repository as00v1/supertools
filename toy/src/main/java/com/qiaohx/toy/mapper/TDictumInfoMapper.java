package com.qiaohx.toy.mapper;

import com.qiaohx.toy.model.TDictumInfo;

public interface TDictumInfoMapper {
    int deleteByPrimaryKey(String id);

    int insert(TDictumInfo record);

    int insertSelective(TDictumInfo record);

    TDictumInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TDictumInfo record);

    int updateByPrimaryKey(TDictumInfo record);
}