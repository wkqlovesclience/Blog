package com.sclience.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.iscliecne.elastic.IBlogElasticService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.sclience.dao.BlogDao;
import com.sclience.entity.Blog;
import com.sclience.service.BlogService;

/**
 * 博客Service实现类
 * @author wangkeqiang
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Resource
	private BlogDao blogDao;
	@Resource
	private IBlogElasticService blogElasticService;
	
	public List<Blog> countList() {
		return blogDao.countList();
	}

	public List<Blog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public Blog findById(Integer id) {
		return blogDao.findById(id);
	}

	public Blog findByIdIgnoreStatus(Integer id){
		return blogDao.findByIdIgnoreStatus(id);
	}

	public Integer update(Blog blog) {
		return blogDao.update(blog);
	}

	public Integer updateToPublishBlog(Blog blog){
		Integer update = blogDao.update(blog);
		com.iscliecne.entity.Blog blogToIndex = new com.iscliecne.entity.Blog();
		BeanUtils.copyProperties(blog,blogToIndex);
		blogToIndex.setReleaseDateStr(sdf.format(blog.getReleaseDate()));
		blogElasticService.addIndex(blogToIndex); // 添加博客索引
		return update;

	}

	public Blog getLastBlog(Integer id) {
		return blogDao.getLastBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		return blogDao.getNextBlog(id);
	}

	public Integer add(Blog blog) {
		return blogDao.add(blog);
	}

	public Integer delete(Integer id) {
		Integer delete = blogDao.delete(id);
		blogElasticService.deleteIndex(id.toString());// 删除对应博客的索引
		return delete;
	}

	

}
