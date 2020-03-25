package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
public class Day9_BiFunction {
    @Test
    public void test(){
        Function<Double, String> area = (x) -> "area is " + x;
        BiFunction<Double, Double, Double> rectangle = (x, y) -> x * y;
        BiFunction<Double, Double, Double> triangle = (a, h) -> a * h / 2;
        Function<Double, Double> cycle = (r) -> r * 3.14159265354;

        log.info("rectangle {}", rectangle.andThen(area).apply(4.0, 5.0));
        log.info("triangle {}", triangle.andThen(area).apply(4.0, 5.0));
        log.info("cycle {}", cycle.andThen(area).apply(4.0));
    }
}
