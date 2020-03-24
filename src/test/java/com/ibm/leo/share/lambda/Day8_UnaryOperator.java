package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Day8_UnaryOperator {

    @Test
    public void test(){
        UnaryOperator<String> threadName = (str) -> "Thread_"+str;
        log.info(threadName.apply("Messenger"));
    }

    @Test
    public void testIdentity(){
        String[] array = {"AAA", "BBB", "CCC", "DDD", "EEE"};
        Map<String, String> map = Stream.of(array)
                .collect(Collectors.toMap(i-> "key" + i,
                        UnaryOperator.identity()));
        log.info(map.toString());
    }
}
