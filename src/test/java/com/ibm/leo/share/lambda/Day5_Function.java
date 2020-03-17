package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;

@Slf4j
public class Day5_Function {

    @Test
    public void test(){
        Function<Integer, String> threadName = (x) -> "Thread_" + x;
        Function<String, String> mailServiceName = (x) -> "MailService_" + x;

        //f.compose(g).apply(x): g(x) result will be the input of f(x)
        String result = mailServiceName.compose(threadName).apply(6);
        log.info(result);

        //f.andThen(g).apply(x): f(x) result will be the input of g(x)
        String result2 = threadName.andThen(mailServiceName).apply(6);
        log.info(result2);

        assertEquals(result, result2);
    }
}
