package com.sclience.service;


import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.pojo.VisitoryTrendPojo;

import java.util.List;

public interface VisitorService {
    public Integer addVisitor(Visitor visitor);
    public Visitor getVisitorByIp(String ip);
    public Integer updateVisitor(Visitor visitor);
    public List<Visitor> getAllVisitor();
    public List<VisitoryPojo> getVisitorInProvinceCount();
    public List<VisitoryTrendPojo> getTrendStatistic(String yearNum);

}
