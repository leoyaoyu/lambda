package com.ibm.leo.share.lambda.Day17_Proxy;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class Tester {

    @Test
    public void test(){
        PICaclculator piRandom = new PIRandom();
        PICaclculator piIncircle = new PIIncircle();
        PICaclculator calculator1 = new PICache(piRandom);
        PICaclculator calculator2 = new PICache(piIncircle);
        log.info("random result : {}", piRandom.calculate().toString());
        log.info("random result : {}", piRandom.calculate().toString());
        log.info("random result : {}", piRandom.calculate().toString());
        log.info("incircle result : {}", piIncircle.calculate().toString());
        log.info("incircle result : {}", piIncircle.calculate().toString());
        log.info("incircle result : {}", piIncircle.calculate().toString());
        log.info("calculator1 result : {}", calculator1.calculate().toString());
        log.info("calculator1 result : {}", calculator1.calculate().toString());
        log.info("calculator1 result : {}", calculator1.calculate().toString());
        log.info("calculator2 result : {}", calculator2.calculate().toString());
        log.info("calculator2 result : {}", calculator2.calculate().toString());
        log.info("calculator2 result : {}", calculator2.calculate().toString());
    }
}
