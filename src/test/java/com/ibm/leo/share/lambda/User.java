package com.ibm.leo.share.lambda;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String name;
    private Integer age;
    private Integer salary;
}