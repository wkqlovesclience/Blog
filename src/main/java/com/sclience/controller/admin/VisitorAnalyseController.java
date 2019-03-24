package com.sclience.controller.admin;

import com.sclience.annotation.BlogLogAnnotation;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.pojo.VisitoryTrendPojo;
import com.sclience.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping("/admin/visitor")
public class VisitorAnalyseController {
    @Autowired
    private VisitorService visitorService;
    @RequestMapping("/visitorStatistic")
    @ResponseBody
    @BlogLogAnnotation(name = "访客区域分析")
    public List<VisitoryPojo> visitorStatistic() {
        List<VisitoryPojo> visitorInProvinceCount = visitorService.getVisitorInProvinceCount();
        return visitorInProvinceCount;
    }
    @RequestMapping("/getTrendChartStatistic")
    @ResponseBody
    @BlogLogAnnotation(name = "访客流量趋势分析")
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
