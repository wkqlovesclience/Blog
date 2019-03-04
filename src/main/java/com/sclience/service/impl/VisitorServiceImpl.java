package com.sclience.service.impl;

import com.sclience.dao.VisitorMapper;
import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;
import com.sclience.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {
    @Autowired
    private VisitorMapper visitorMapper;


    public Integer addVisitor(Visitor visitor){
        int insert = this.visitorMapper.insert(visitor);
        return insert;
    }

    public Visitor getVisitorByIp(String ip) {
        return visitorMapper.getVisitorByIp(ip);
    }

    public Integer updateVisitor(Visitor visitor) {
        return visitorMapper.updateByPrimaryKey(visitor);
    }

    public List<Visitor> getAllVisitor() {
        return visitorMapper.getAllVisitor();
    }

    public List<VisitoryPojo> getVisitorInProvinceCount(){
        return visitorMapper.getVisitorInProvinceCount();

    }
}
