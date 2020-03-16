package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static junit.framework.TestCase.assertTrue;

@Slf4j
public class Day3_Default_Lambda_Functional_Interface {

    Supplier<String> supplier = () -> "this is a supplier to provide a str";
    Function<Integer, Integer> function = (input) -> input * input;
    Consumer<String> consumer = (name) -> {log.info("Hello {}, welcome to join lambda world!",name);};
    Predicate<Integer> predicate = (number) -> number > 0;

    @Test
    public void test(){
        int x = 5;
        log.info("Supplier output : {}", supplier.get());
        log.info("function output {}*{}={}",x, x, function.apply(x));
        consumer.accept("Shanea");
        assertTrue(predicate.test(x));
    }

}
