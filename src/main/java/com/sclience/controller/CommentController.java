package com.sclience.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sclience.aop.LogAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sclience.entity.Blog;
import com.sclience.entity.Comment;
import com.sclience.service.BlogService;
import com.sclience.service.CommentService;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 评论Controller层
 *
 * @author wangkeqiang
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @Resource
    private BlogService blogService;

    /**
     * 添加或者修改评论
     *
     * @param comment
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @LogAnnotation(name = "添加或者修改评论")
    @ResponseBody
    public Object save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request, HttpSession session) throws Exception {
        String sRand = (String) session.getAttribute("sRand"); // 获取系统生成的验证码
        JSONObject result = new JSONObject();
        int resultTotal = 0; // 操作的记录条数
        if (!imageCode.equals(sRand)) {
            result.put("success", false);
            result.put("errorInfo", "验证码填写错误！");
        } else {
            String userIp = request.getRemoteAddr(); // 获取用户IP
            comment.setUserIp(userIp);
            if (comment.getId() == null) {
                resultTotal = commentService.add(comment);
                // 该博客的回复次数加1
                Blog blog = blogService.findById(comment.getBlog().getId());
                blog.setReplyHit(blog.getReplyHit() + 1);
                blogService.update(blog);
            } else {

            }
            if (resultTotal > 0) {
                result.put("success", true);
            } else {
                result.put("success", false);
            }
        }
        return result;
    }

}
