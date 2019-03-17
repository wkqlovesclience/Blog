package com.sclience.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sclience.dao.BloggerDao;
import com.sclience.entity.Blogger;
import com.sclience.service.BloggerService;

/**
 * 博主Service实现类
 * @author wangkeqiang
 *
 */
@Service("bloggerService")
public class BloggerServiceImpl implements BloggerService{

	@Resource
	private BloggerDao bloggerDao;

	public Blogger find() {
		return bloggerDao.find();
	}

	public Blogger getByUserName(String userName) {
		return bloggerDao.getByUserName(userName);
	}

	public Integer update(Blogger blogger) {
		return bloggerDao.update(blogger);
	}
	
	
}
