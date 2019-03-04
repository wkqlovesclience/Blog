package com.sclience.service;


import com.sclience.entity.Visitor;

public interface VisitorService {
    public Integer addVisitor(Visitor visitor);
    public Visitor getVisitorByIp(String ip);
    public Integer updateVisitor(Visitor visitor);

}
