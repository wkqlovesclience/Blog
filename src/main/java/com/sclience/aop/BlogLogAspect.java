package com.sclience.aop;


import com.sclience.annotation.BlogLogAnnotation;
import com.sclience.entity.BlogLog;
import com.sclience.entity.Blogger;
import com.sclience.service.BlogLogService;
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
import java.lang.reflect.Method;
import java.util.Date;
@Configuration
@Aspect
public class BlogLogAspect {
    @Autowired
    private BlogLogService blogLogService;
    //定义切入点
    @Pointcut(value = "@annotation(com.sclience.annotation.BlogLogAnnotation)")
    public void poinCut() {
    } 
    //定义环绕通知
    @Around(value = "poinCut()") //引入切入点
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        HttpSession session = request.getSession();
        Blogger bloger = (Blogger) session.getAttribute("currentUser");
        //获取方法名对象  基于反射
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();  //获取到方法对象
        // 通过方法对象拿到方法上的注解对象
        BlogLogAnnotation  annotation = method.getAnnotation(BlogLogAnnotation.class);
        //拿到注解的属性值
        String annotationName = annotation.name();
        BlogLog blogLog = new BlogLog();
        blogLog.setLogOperatorId(bloger.getId());
        blogLog.setLogTitle(annotationName);
        blogLog.setLogDate(new Date());
        Object proceed=null;
        try {
            proceed = proceedingJoinPoint.proceed();
            blogLog.setLogContent("操作成功");
        } catch (Throwable throwable) {
            blogLog.setLogContent("操作失敗");
            throwable.printStackTrace();
        }
        blogLogService.insertBlogLog(blogLog);
        return proceed;

    }


}
