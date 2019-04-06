package com.sclience.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.sclience.annotation.BlogLogAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sclience.entity.BlogType;
import com.sclience.entity.PageBean;
import com.sclience.service.BlogTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员博客类别Controller层
 * @author wangkeqiang
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	
	/**
	 * 分页查询博客类别信息
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
		List<BlogType> blogTypeList=blogTypeService.list(map);
		Long total=blogTypeService.getTotal(map);
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(blogTypeList);
		result.put("rows", jsonArray);
		result.put("total", total);
		return result;
	}
	
	/**
	 * 添加或者修改博客类别信息
	 * @param blogType
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
    @BlogLogAnnotation(name = "添加或者修改博客类别信息")
	public Object save(BlogType blogType)throws Exception{
		int resultTotal=0; // 操作的记录条数
		if(blogType.getId()==null){
			resultTotal=blogTypeService.add(blogType);
		}else{
			resultTotal=blogTypeService.update(blogType);
		}
		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		return result;
	}
	
	/**
	 * 删除博客类别信息
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	@ResponseBody
    @BlogLogAnnotation(name = "删除博客类别信息")
	public Object delete(@RequestParam(value="ids")String ids)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogTypeService.delete(Integer.parseInt(idsStr[i]));
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		return result;
	}
}
