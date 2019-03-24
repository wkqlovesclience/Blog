package com.sclience.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lenovo on 2018/7/10.
 */


//定义一个自定义注解

@Target(ElementType.METHOD)  //这里加载方法上  这个注解是 指定 这个注解加载那（ 方法上 属性上 或者构造方法）
@Retention(RetentionPolicy.RUNTIME) //声明当前注解的类型   这里是运行时
public @interface VisitorAnnotation {
    public String name();  //这是一个属性    String 为属性的类型  name 为属性名字
}
