package com.example.mapper;

import com.example.domain.AppDebugLog;

public interface AppDebugLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AppDebugLog record);

    int insertSelective(AppDebugLog record);

    AppDebugLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AppDebugLog record);

    int updateByPrimaryKeyWithBLOBs(AppDebugLog record);

    int updateByPrimaryKey(AppDebugLog record);
}