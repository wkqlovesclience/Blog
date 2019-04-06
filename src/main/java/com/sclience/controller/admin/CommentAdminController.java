package com.sclience.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.sclience.annotation.BlogLogAnnotation;
import com.sclience.entity.Blogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sclience.entity.Comment;
import com.sclience.entity.PageBean;
import com.sclience.service.CommentService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员评论Controller层
 *
 * @author wangkeqiang
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

    @Resource
    private CommentService commentService;

    /**
     * 分页查询评论信息
     *
     * @param page
     * @param rows
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "rows", required = false) String rows, HttpServletRequest request) throws Exception {

        Blogger currentUser = (Blogger) request.getSession().getAttribute("currentUser");
        PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        if (currentUser != null && !currentUser.getManagerRole().equals("admin")) {
            map.put("bloggerId",currentUser.getId());
        }
        List<Comment> commentList = commentService.list(map);
        Long total = commentService.getTotal(map);
        JSONObject result = new JSONObject();
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSONArray jsonArray = JSONArray.fromObject(commentList, jsonConfig);
        result.put("rows", jsonArray);
        result.put("total", total);
        return result;
    }

    /**
     * 删除评论信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    @ResponseBody
    @BlogLogAnnotation(name = "删除评论信息")
    public Object delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Comment comment = commentService.findByIdIgnoreStatus(Integer.parseInt(idsStr[i]));
            comment.setCommentStatus(2);
            commentService.updateComment(comment);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }


    /**
     * 发布评论信息
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping("/publish")
    @ResponseBody
    @BlogLogAnnotation(name = "发布评论")
    public Object publish(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            Comment comment = commentService.findByIdIgnoreStatus(Integer.parseInt(idsStr[i]));
            comment.setCommentStatus(1);
            commentService.updateComment(comment);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        return result;
    }
}
