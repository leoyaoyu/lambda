package com.ibm.leo.share.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class Day12_SelfLambda {

    @FunctionalInterface
    public interface Calculate<T> {
        T algorithm(T x, T y);

        default BinaryOperator<T> andThen(Function<? super T, ? extends T> after){
            Objects.requireNonNull(after);
            return (T x, T y) -> after.apply(algorithm(x, y));
        }

        default BiConsumer<T, T> andThen(Consumer<? super T> after){
            Objects.requireNonNull(after);
            return (T x, T y) -> {
                T z = algorithm(x, y);
                after.accept(z);
            };
        }

        static Class<Calculate> getCalulateClass(){
            return Calculate.class;
        }
    }

    @Test
    public void test1(){
        Integer x = 8, y =9;
        Calculate<Integer> calculate = (a1, a2) -> a1 + a2;
        Function<Integer, Integer> function = (a3)-> a3 + 1;
        log.info(calculate.andThen(function).apply(x, y).toString());

    }

    @Test
    public void test2(){
        Integer x = 8, y =9;
        Calculate<Integer> calculate = (a1, a2) -> a1 + a2;
        Consumer<Integer> print = (in) -> log.info(in.toString());
        calculate.andThen(print).accept(x, y);
    }

    @Test
    public void test3(){
        log.info(Calculate.getCalulateClass().toString());
    }
}
