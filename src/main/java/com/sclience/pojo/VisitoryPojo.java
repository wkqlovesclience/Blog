package com.sclience.pojo;

public class VisitoryPojo {
    private String name;
    private Integer value;

    public VisitoryPojo() {
    }

    public VisitoryPojo(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
