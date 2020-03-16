package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Predicate;

import static junit.framework.TestCase.assertTrue;

@Slf4j
public class Day4_Predicate {

    public static final String SERVICE_NAME1 = "MAIL_SERVICE";
    public static final String SERVICE_NAME2 = "TEMPLATE_SERVICE";

    @Test
    public void test(){
        String input = "TEMPLATE_SERVICE";
        Predicate<String> p1 = (str) -> str == null;
        Predicate<String> p2 = (str) -> !str.isEmpty();
        Predicate<String> p3 = (str) -> str.equals(SERVICE_NAME1);
        Predicate<String> p4 = (str) -> str.equals(SERVICE_NAME2);
        boolean result = p1.negate().and(p2).and(p3.or(p4)).test(input);
        log.info("{} is valid service included in {} or {}", input, SERVICE_NAME1,SERVICE_NAME2);
        log.info("Predicate test result is {}", result);
        assertTrue(result);
    }
}
