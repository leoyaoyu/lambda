package com.ibm.leo.share.lambda.Day15_Builder;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Tester {

    @Test
    public void testEmployee1() {
        Employee1 e1 = Employee1.builder()
                .name("Leo")
                .age(60)
                .salary(10.00)
                .build();

        Employee2 e2 = Employee2.builder()
                .name("Leo")
                .age(60)
                .salary(10.00)
                .build();

        Employee3 e3 = Employee3.build()
                .name("Leo")
                .age(60)
                .salary(10.00);

        log.info(e1.toString());
        log.info(e2.toString());
        log.info(e3.toString());
    }
}
