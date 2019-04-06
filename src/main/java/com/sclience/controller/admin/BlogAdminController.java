package com.sclience.controller.admin;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import com.sclience.annotation.BlogLogAnnotation;
import com.sclience.entity.Blogger;
import com.sclience.service.BloggerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sclience.entity.Blog;
import com.sclience.entity.PageBean;
import com.sclience.service.BlogService;
import com.sclience.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 管理员博客Controller层
 *
 * @author wangkeqiang
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;
    @Resource
    private BloggerService bloggerService;


    /**
     * 添加或者修改博客信息
     *
     * @param blog
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @ResponseBody
    @BlogLogAnnotation(name = "添加或者修改博客信息")
    public Object save(Blog blog, HttpServletResponse response) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (blog.getId() == null) {
            resultTotal = blogService.add(blog);
        } else {
            Blogger blogger = bloggerService.getBloggerByPrimaryKey(blog.getBlogger().getId());
            Blog blogById = blogService.findById(blog.getId());
            blog.setBlogger(blogger);
            blog.setReleaseDate(blogById.getReleaseDate());
            resultTotal = blogService.update(blog);
        }
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 分页查询博客信息
     *
     * @param page
     * @param rows
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, Blog s_blog,String managerRole,String bloggerId, HttpServletResponse response) throws Exception {
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", StringUtil.formatLike(s_blog.getTitle()));
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        if (!managerRole.equals("admin")){
            map.put("bloggerId",bloggerId);
        }
        List<Blog> blogList = blogService.list(map);
        Long total = blogService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(blogList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        return result;
    }

    /**
     * 删除博客信息
     *
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    @BlogLogAnnotation(name = "刪除博客信息")
    public Object delete(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (String s : idsStr) {
            blogService.delete(Integer.parseInt(s));
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }

    /**
     * 发布博客信息
     *
     * @param ids
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/publish")
    @ResponseBody
    @BlogLogAnnotation(name = "发布博客")
    public Object publish(@RequestParam(value = "ids") String ids, HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Blog blog = blogService.findByIdIgnoreStatus(Integer.parseInt(idsStr[i]));
            blog.setBlogStatus(1);
            blogService.updateToPublishBlog(blog);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }


    /**
     * 通过ID查找实体
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/findById")
    @ResponseBody
    @BlogLogAnnotation(name = "通過ID查找实体")
    public Object findById(@RequestParam(value = "id") String id) throws Exception {
        Blog blog = blogService.findById(Integer.parseInt(id));
        return JSONObject.fromObject(blog);
    }

}
