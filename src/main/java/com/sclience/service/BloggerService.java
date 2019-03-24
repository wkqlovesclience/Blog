package com.sclience.service;

import com.sclience.entity.Blogger;

import java.util.List;

/**
 * 博主Service接口
 * @author wangkeqiang
 *
 */
public interface BloggerService {

	/**
	 * 查询博主信息
	 * @return
	 */
	public Blogger find();
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * 更新博主信息
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);

	/**
	 * 查询全部博主
	 */

	public List<Blogger> getAllBlogger();

	/**
	 * 通过id查询博主
	 */

	public Blogger getBloggerByPrimaryKey(Integer id);


}
