package com.sclience.controller.admin;

import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.pojo.VisitoryTrendPojo;
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



    @RequestMapping("/visitorStatistic")
    @ResponseBody
    public List<VisitoryPojo> visitorStatistic() {
        List<VisitoryPojo> visitorInProvinceCount = visitorService.getVisitorInProvinceCount();
        return visitorInProvinceCount;
    }


    @RequestMapping("/activeUser")
    @ResponseBody
    public Map<String ,Object> activeUser(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("intervals",new String[]{"5天","15天","30天","半年","一年",});
        map.put("count",new int[]{10,15,20,27,49});
        return map;
    }


    @RequestMapping("/getTrendChartStatistic")
    @ResponseBody
    public Map<String,Object> getTrendChartStatistic(String yearNum){
        if (yearNum==null){
            yearNum = getSysYear();
        }
        List<VisitoryTrendPojo> visitoryTrendPojos = visitorService.getTrendStatistic(yearNum);
        String[] month = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12"};
        Integer[] count = new Integer[]{0,0,0,0,0,0,0,0,0,0,0,0};
        for (int i = 0; i < visitoryTrendPojos.size(); i++) {
            int monthNumberIndex = Integer.valueOf(visitoryTrendPojos.get(i).getMonth())-1;
            count[monthNumberIndex] = visitoryTrendPojos.get(i).getVositorCount();
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("intervals",month);
        map.put("count",count);
        return map;

    }

    public String getSysYear() {
        Calendar date = Calendar.getInstance();
        String year = String.valueOf(date.get(Calendar.YEAR));
        return year;
    }
}
