package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.stream.Stream;

@Slf4j
public class Day6_Consumer {

    @Test
    public void test(){
        Exception ex = new NullPointerException("can not find the user with id AD34CVER3");

        //output detailed log stack trace in debug log;
        Consumer<Exception> detailedExceptionLog = (ex1) ->{
            StackTraceElement[] elements = ex1.getStackTrace();
            Stream.of(elements).forEach(trace ->
                log.debug("User found exception: {}", trace)
            );
        };

        //only output exception message in warning log;
        Consumer<Exception> exceptionLog = (ex2) -> log.warn("User found exception: {}", ex2.getMessage());

        //output both kinds of logs
        exceptionLog.andThen(detailedExceptionLog).accept(ex);
    }
}
