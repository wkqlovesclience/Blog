package com.sclience.controller.admin;

import java.io.File;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sclience.annotation.BlogLogAnnotation;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sclience.entity.Blogger;
import com.sclience.service.BloggerService;
import com.sclience.util.CryptographyUtil;
import com.sclience.util.DateUtil;
import net.sf.json.JSONObject;

/**
 * 管理员博主Controller层
 *
 * @author wangkeqiang
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

    @Resource
    private BloggerService bloggerService;

    /**
     * 修改博主信息
     *
     * @param blogger
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/save")
    @BlogLogAnnotation(name = "修改博主信息")
    public Object save(@RequestParam("imageFile") MultipartFile imageFile, Blogger blogger, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!imageFile.isEmpty()) {
            String filePath = request.getServletContext().getRealPath("/");
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "static/userImages/" + imageName));
            blogger.setImageName(imageName);
        }
        int resultTotal = bloggerService.update(blogger);
        StringBuffer result = new StringBuffer();
        if (resultTotal > 0) {
            result.append("<script language='javascript'>alert('修改成功！');</script>");
        } else {
            result.append("<script language='javascript'>alert('修改失败！');</script>");
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.println(result.toString());
        writer.close();
        return null;
    }

    /**
     * 查询博主信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/find")
    @ResponseBody
    @BlogLogAnnotation(name = "查询博主信息")
    public Object find() throws Exception {
        return JSONObject.fromObject(bloggerService.find());
    }

    /**
     * 修改博主密码
     *
     * @param newPassword
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    @BlogLogAnnotation(name = "修改博主密码")
    public Object modifyPassword(Integer id ,String newPassword, HttpServletResponse response) throws Exception {
        Blogger blogger = bloggerService.getBloggerByPrimaryKey(id);
        blogger.setPassword(CryptographyUtil.md5(newPassword, "sclience"));
        int resultTotal = bloggerService.update(blogger);
        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 注销
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    @BlogLogAnnotation(name = "注销")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.jsp";
    }
}
