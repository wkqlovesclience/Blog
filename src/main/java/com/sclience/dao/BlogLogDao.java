package com.sclience.dao;

import com.sclience.entity.BlogLog;

import java.util.List;
import java.util.Map;

public interface BlogLogDao {
    int deleteByPrimaryKey(Integer logId);

    int insert(BlogLog record);

    int insertSelective(BlogLog record);

    BlogLog selectByPrimaryKey(Integer logId);

    int updateByPrimaryKeySelective(BlogLog record);

    int updateByPrimaryKey(BlogLog record);

    /**
     * 查找用户操作记录信息
     * @param map
     * @return
     */
    public List<BlogLog> list(Map<String,Object> map);

    /**
     * 获取总记录数
     * @param map
     * @return
     */
    public Long getTotal(Map<String,Object> map);
}