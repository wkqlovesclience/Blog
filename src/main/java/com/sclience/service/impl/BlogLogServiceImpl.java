package com.sclience.service.impl;

import com.sclience.dao.BlogLogDao;
import com.sclience.entity.BlogLog;
import com.sclience.service.BlogLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class BlogLogServiceImpl implements BlogLogService {
    @Autowired
    private BlogLogDao blogLogDao;

    @Transactional
    public Integer insertBlogLog(BlogLog blogLog) {
        return blogLogDao.insert(blogLog);
    }

    public List<BlogLog> list(Map<String, Object> map) {
        return blogLogDao.list(map);
    }

    public Long getTotal(Map<String, Object> map) {
        return blogLogDao.getTotal(map);
    }

    public Integer delete(Integer id) {
        return blogLogDao.deleteByPrimaryKey(id);
    }
}
