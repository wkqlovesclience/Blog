package com.sclience.controller.admin;

import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@Controller
@RequestMapping("/admin/visitor")
public class VisitorAnalyseController {
    @Autowired
    private VisitorService visitorService;

    @RequestMapping("/userAnalyse")
    @ResponseBody
    public String userAnalyse(){
        Map<String,String> map = new HashMap<String,String>();

        return null;
    }



    @RequestMapping("/maleStatistic")
    @ResponseBody
    public List<VisitoryPojo> maleStatistic() {
        List<VisitoryPojo> visitorInProvinceCount = visitorService.getVisitorInProvinceCount();
        return visitorInProvinceCount;
    }


    @RequestMapping("/femaleStatistic")
    @ResponseBody
    public List<VisitoryPojo> femaleStatistic() {
        ArrayList<VisitoryPojo> list = new ArrayList<VisitoryPojo>();
        list.add(new VisitoryPojo("上海",90));
        list.add(new VisitoryPojo("河南",30));
        list.add(new VisitoryPojo("西藏",80));
        list.add(new VisitoryPojo("北京",190));
        list.add(new VisitoryPojo("云南",10));
        list.add(new VisitoryPojo("新疆",9));
        list.add(new VisitoryPojo("山西",9));
        list.add(new VisitoryPojo("山西",9));
        list.add(new VisitoryPojo("河北",9));
        return list;
    }

    @RequestMapping("/activeUser")
    @ResponseBody
    public Map<String ,Object> activeUser(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("intervals",new String[]{"5天","15天","30天","半年","一年",});
        map.put("count",new int[]{10,15,20,27,49});
        return map;
    }
}
