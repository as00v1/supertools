package com.qiaohx.wechat.mapper;

import com.qiaohx.wechat.model.WMpInfo;

import java.util.List;

public interface WMpInfoMapper {
    int deleteByPrimaryKey(String mpId);

    int insert(WMpInfo record);

    int insertSelective(WMpInfo record);

    WMpInfo selectByPrimaryKey(String mpId);

    int updateByPrimaryKeySelective(WMpInfo record);

    int updateByPrimaryKey(WMpInfo record);

    List<WMpInfo> queryAll();
}