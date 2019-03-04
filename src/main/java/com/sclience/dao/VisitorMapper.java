package com.sclience.dao;

import com.sclience.entity.Visitor;
import com.sclience.pojo.VisitoryPojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface VisitorMapper {
    int deleteByPrimaryKey(Integer visitorId);

    int insert(Visitor record);

    int insertSelective(Visitor record);

    Visitor selectByPrimaryKey(Integer visitorId);

    int updateByPrimaryKeySelective(Visitor record);

    int updateByPrimaryKey(Visitor record);

    Visitor getVisitorByIp(String ip);

    List<Visitor> getAllVisitor();

    List<VisitoryPojo> getVisitorInProvinceCount();
}