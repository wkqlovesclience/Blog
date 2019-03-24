package com.sclience.entity;

/**
 * 博主实体
 * @author wangkeqiang
 *
 */
public class Blogger {
	private Integer id;
	private String userName;
	private String password;
	private String nickName;
	private String sign;
	private String proFile;
	private String imageName;
	private Integer blogNum;
	private String managerRole;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getProFile() {
		return proFile;
	}

	public void setProFile(String proFile) {
		this.proFile = proFile;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getBlogNum() {
		return blogNum;
	}

	public void setBlogNum(Integer blogNum) {
		this.blogNum = blogNum;
	}

	public String getManagerRole() {
		return managerRole;
	}

	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
}
