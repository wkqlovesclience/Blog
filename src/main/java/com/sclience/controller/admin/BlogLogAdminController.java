package com.sclience.controller.admin;

import com.sclience.annotation.BlogLogAnnotation;
import com.sclience.entity.BlogLog;
import com.sclience.entity.Comment;
import com.sclience.entity.PageBean;
import com.sclience.service.BlogLogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员评论Controller层
 * @author wangkeqiang
 *
 */
@Controller
@RequestMapping("/admin/blogLog")
public class BlogLogAdminController {

	@Resource
	private BlogLogService blogLogService;
	
	/**
	 * 分页查询操作记录信息
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Object list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogLog> blogLogList= blogLogService.list(map);
		Long total= blogLogService.getTotal(map);
		JSONObject result=new JSONObject();
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONArray jsonArray=JSONArray.fromObject(blogLogList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", total);
		return result;
	}
	
	/**
	 * 删除操作记录信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
    @BlogLogAnnotation(name = "删除操作记录信息")
	public Object delete(@RequestParam(value="ids")String ids) throws NullPointerException{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogLogService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		return result;
	}
}
