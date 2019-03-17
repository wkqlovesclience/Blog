package com.sclience.aop;

import com.alibaba.fastjson.JSONObject;
import com.sclience.entity.Visitor;
import com.sclience.service.VisitorService;
import com.sclience.util.AddressUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Configuration
@Aspect
public class LogAspect {
    @Autowired
    private VisitorService visitorService;
    //定义切入点
    @Pointcut(value = "@annotation(LogAnnotation)")
    public void poinCut() {

    } 
    //定义环绕通知
    @Around(value = "poinCut()") //引入切入点
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userIp=request.getRemoteAddr();
        String referer = request.getHeader("referer");
        try {
            Integer visitCount = 1;
            Visitor visitorByIp = visitorService.getVisitorByIp(userIp);
            if (visitorByIp==null){
                JSONObject address = AddressUtil.getAddresses("ip=" + userIp, "utf-8");
                JSONObject data = address.getJSONObject("data");
                String country = data.getString("country");
                String region = data.getString("region");
                String city = data.getString("city");
                String area = data.getString("area");
                Date date = new Date();
                Visitor visitor = new Visitor(null,userIp,referer,country,region,city,area,date,visitCount);
                visitorService.addVisitor(visitor);
            }else {
                if (referer==null){
                    visitorByIp.setVisitorCount(visitorByIp.getVisitorCount()+1);
                }else if (!referer.contains("Blog/")){
                    visitorByIp.setVisitorCount(visitorByIp.getVisitorCount()+1);
                }
                visitorService.updateVisitor(visitorByIp);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpSession session = request.getSession();
        Object proceed=null;
        try {
            proceed = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return proceed;

    }


}
