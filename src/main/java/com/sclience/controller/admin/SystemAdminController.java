package com.sclience.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;
import com.sclience.entity.Blog;
import com.sclience.entity.BlogType;
import com.sclience.entity.Blogger;
import com.sclience.service.BlogService;
import com.sclience.service.BlogTypeService;
import com.sclience.service.BloggerService;
import net.sf.json.JSONObject;

/**
 * 管理员系统Controller层
 * @author wangkeqiang
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 刷新系统缓存
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/refreshSystem")
	@ResponseBody
	public Object refreshSystem(HttpServletRequest request)throws Exception{
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		Blogger blogger=bloggerService.find(); // 查询博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
		application.setAttribute("blogCountList", blogCountList);
		JSONObject result=new JSONObject();
		result.put("success", true);
		return result;
	}
}
