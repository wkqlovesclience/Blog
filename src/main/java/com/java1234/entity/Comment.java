package com.java1234.entity;

import java.util.Date;

/**
 * ����ʵ��
 * @author Administrator
 *
 */
public class Comment {

	private Integer id; // ���
	private String userIp; // �û�IP
	private String content; // ��������
	private Blog blog; // �����۵Ĳ���
	private Date commentDate; // ��������
	
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
	
	
}
