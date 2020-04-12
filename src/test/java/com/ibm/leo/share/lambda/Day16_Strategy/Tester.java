package com.ibm.leo.share.lambda.Day16_Strategy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Supplier;

@Slf4j
public class Tester {

    @Test
    public void test(){
        PI pi = new PI();
        log.info("PI 1 value is : {}", pi.getPI(new PIRandom()));
        log.info("PI 2 value is : {}", pi.getPI(new PIIncircle()));

        PI2 pi2 = new PI2();
        log.info("PI 1 value is : {}", pi2.getPI(PI2.random));
        log.info("PI 2 value is : {}", pi2.getPI(PI2.incircle));
    }

}
