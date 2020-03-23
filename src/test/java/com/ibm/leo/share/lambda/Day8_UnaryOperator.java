package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.UnaryOperator;

@Slf4j
public class Day8_UnaryOperator {

    @Test
    public void test(){
        UnaryOperator<String> threadName = (str) -> "Thread_"+str;
        log.info(threadName.apply("Messenger"));
    }

}
