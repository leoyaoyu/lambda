package com.ibm.leo.share.lambda;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

@Slf4j
public class Day1_basic {

    int x = 8, y = 9;

    @FunctionalInterface
    public interface Calculate {
        Integer algorithm(Integer x, Integer y);
    }

    @Test
    public void test(){
        //lambda
        Calculate addition = (x, y) -> x + y;
        log.info("lambda compare result: {}", addition.algorithm(x,y));

        //inner class
        Calculate addition0 = new Calculate() {
            @Override
            public Integer algorithm(Integer x, Integer y) {
                return x + y;
            }
        };
        log.info("inner class compare result: {}", addition0.algorithm(x, y));

        assertTrue(addition.algorithm(x,y) == addition0.algorithm(x, y));
    }

}
