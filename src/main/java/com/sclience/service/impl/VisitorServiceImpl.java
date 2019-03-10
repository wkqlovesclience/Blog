package com.sclience.service.impl;

import com.sclience.dao.VisitorDao;
import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.pojo.VisitoryTrendPojo;
import com.sclience.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorDao visitorDao;


    public Integer addVisitor(Visitor visitor){
        int insert = this.visitorDao.insert(visitor);
        return insert;
    }

    public Visitor getVisitorByIp(String ip) {
        return visitorDao.getVisitorByIp(ip);
    }

    public Integer updateVisitor(Visitor visitor) {
        return visitorDao.updateByPrimaryKey(visitor);
    }

    public List<Visitor> getAllVisitor() {
        return visitorDao.getAllVisitor();
    }

    public List<VisitoryPojo> getVisitorInProvinceCount(){
        return visitorDao.getVisitorInProvinceCount();

    }
    public List<VisitoryTrendPojo> getTrendStatistic(String yearNum) {
        return visitorDao.getVisitorTrend(yearNum);
    }
}
