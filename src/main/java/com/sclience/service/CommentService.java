package com.sclience.service;

import java.util.List;
import java.util.Map;

import com.sclience.entity.Comment;

/**
 * 评论Service接口
 * @author wangkeqiang
 *
 */
public interface CommentService {

	/**
	 * 添加评论
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);
	
	/**
	 * 查找用户评论信息
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 删除评论信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);

	/**
	 * 根据主键查询评论
	 * @param id
	 * @return
	 */
	public Comment findByIdIgnoreStatus(Integer id);

	public Integer updateComment(Comment comment);
}
