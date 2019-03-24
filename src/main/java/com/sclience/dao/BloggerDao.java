package com.sclience.dao;

import com.sclience.entity.Blogger;

import java.util.List;

/**
 * 博主Dao接口
 *
 * @author wangkeqiang
 */
public interface BloggerDao {

    /**
     * 根据用户名查询博主信息
     */
    public Blogger findById(Integer id);

    /**
     * 查询博主信息
     *
     * @return
     */
    public Blogger find();

    /**
     * 查询全部博主信息
     *
     * @return
     */
    public List<Blogger> findAll();

    /**
     * 通过用户名查询用户
     *
     * @param userName
     * @return
     */
    public Blogger getByUserName(String userName);

    /**
     * 更新博主信息
     *
     * @param blogger
     * @return
     */
    public Integer update(Blogger blogger);
}
