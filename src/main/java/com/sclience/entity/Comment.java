package com.sclience.entity;

import java.util.Date;

/**
 * 评论实体
 * @author wangkeqiang
 *
 */
public class Comment {

	private Integer id; // 编号
	private String userIp; // 用户IP
	private String content; // 评论内容
	private Blog blog; // 被评论的博客
	private Date commentDate; // 评论日期
	private Integer commentStatus;//评论状态  0 已提交  1已通过  2已删除
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Blog getBlog() {
		return blog;
	}
	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public Integer getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(Integer commentStatus) {
		this.commentStatus = commentStatus;
	}
}
