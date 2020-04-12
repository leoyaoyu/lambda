package com.ibm.leo.share.lambda.Day15_Builder;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee1 {
    private String id;
    private String name;
    private Integer age;
    private Double salary;
    private long hireDate;
    private String DepartmentId;
    private String reportManagerId;
}
