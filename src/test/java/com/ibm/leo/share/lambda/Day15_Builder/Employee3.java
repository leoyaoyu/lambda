package com.ibm.leo.share.lambda.Day15_Builder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee3 {
    private String id;
    private String name;
    private Integer age;
    private Double salary;
    private long hireDate;
    private String DepartmentId;
    private String reportManagerId;

    private Employee3(){
    }

    public static Employee3 build(){
        return new Employee3();
    }

    public Employee3 id(String id) {
        this.id = id;
        return this;
    }

    public Employee3 name(String name) {
        this.name = name;
        return this;
    }

    public Employee3 age(Integer age) {
        this.age = age;
        return this;
    }

    public Employee3 salary(Double salary) {
        this.salary = salary;
        return this;
    }

    public Employee3 hireDate(long hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public Employee3 departmentId(String departmentId) {
        DepartmentId = departmentId;
        return this;
    }

    public Employee3 reportManagerId(String reportManagerId) {
        this.reportManagerId = reportManagerId;
        return this;
    }
}
