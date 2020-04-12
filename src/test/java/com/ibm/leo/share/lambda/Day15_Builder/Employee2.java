package com.ibm.leo.share.lambda.Day15_Builder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employee2 {
    private String id;
    private String name;
    private Integer age;
    private Double salary;
    private long hireDate;
    private String DepartmentId;
    private String reportManagerId;

    public static Employee2.Builder builder() {
        return new Employee2.Builder();
    }

    public static class Builder{
        private String id;
        private String name;
        private Integer age;
        private Double salary;
        private long hireDate;
        private String DepartmentId = "0086000001";
        private String reportManagerId;

        public Employee2 build() throws MandatoryParamException {
            Employee2 employee2 = new Employee2();
            if(this.id != null) employee2.setId(this.id);
            if(this.name != null) employee2.setName(this.name);
            else throw new MandatoryParamException("name");
            if(this.age > 0) employee2.setAge(this.age);
            if(this.salary != null) employee2.setSalary(this.salary);
            if(this.hireDate > 0) employee2.setHireDate(this.hireDate);
            if(this.DepartmentId != null) employee2.setDepartmentId(this.DepartmentId);
            if(this.reportManagerId != null) employee2.setReportManagerId(this.reportManagerId);
            return employee2;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder salary(Double salary) {
            this.salary = salary;
            return this;
        }

        public Builder hireDate(long hireDate) {
            this.hireDate = hireDate;
            return this;
        }

        public Builder departmentId(String departmentId) {
            DepartmentId = departmentId;
            return this;
        }

        public Builder reportManagerId(String reportManagerId) {
            this.reportManagerId = reportManagerId;
            return this;
        }
    }
}
