package com.sclience.service;

import com.sclience.entity.BlogLog;

import java.util.List;
import java.util.Map;

public interface BlogLogService {
    public Integer insertBlogLog(BlogLog blogLog);

    /**
     * 查找用户评论信息
     *
     * @param map
     * @return
     */
    public List<BlogLog> list(Map<String, Object> map);

    /**
     * 获取总记录数
     *
     * @param map
     * @return
     */
    public Long getTotal(Map<String, Object> map);

    /**
     * 删除评论信息
     *
     * @param id
     * @return
     */
    public Integer delete(Integer id);
}
